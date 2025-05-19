package com.capi.shop.adapter;

import com.capi.shop.domain.model.Category;
import com.capi.shop.domain.model.Product;
import com.capi.shop.domain.repository.CategoryRepository;
import com.capi.shop.domain.repository.ProductRepository;
import com.capi.shop.domain.service.PricingService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import java.math.BigDecimal;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductRepository productRepo;

    @Inject
    CategoryRepository categoryRepo;

    @Inject
    PricingService pricingService;

    /**
     * List products, optionally filtered by category (jsonId) and sorted by one of
     * "sku", "basePrice", "description", "category".
     * Applies the highest discount rule per product.
     */
    @GET
    public Uni<List<ProductResponse>> list(
            @RestQuery String category,
            @RestQuery String sortBy
    ) {
        // Step 1: determine which Category (if any)
        Uni<Multi<Product>> source = (category != null)
                ? categoryRepo.findByJsonId(category)
                .map(cat -> productRepo.findAll(cat, sortBy))
                : Uni.createFrom().item(productRepo.findAll(null, sortBy));

        // Step 2: for each product, calculate effective price and map to DTO
        return source.flatMap(multi ->
                multi.onItem().transformToUni(product ->
                                pricingService.calculateEffectivePrice(product)
                                        .map(price -> toResponse(product, price))
                        )
                        .collect().asList()
        );
    }

    private static ProductResponse toResponse(Product p, BigDecimal effectivePrice) {
        return new ProductResponse(
                p.getId(),
                p.getSku(),
                p.getDescription(),
                p.getCategory().jsonId(),
                p.getCategory().name(),
                p.getBasePrice(),
                effectivePrice
        );
    }

    /** DTO returned by GET /products */
    public record ProductResponse(
            Long    id,
            String  sku,
            String  description,
            String  categoryJsonId,
            String  categoryName,
            BigDecimal basePrice,
            BigDecimal effectivePrice
    ) {}
}
