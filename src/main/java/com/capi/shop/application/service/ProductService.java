package com.capi.shop.application.service;

import com.capi.shop.application.dto.ProductResponse;
import com.capi.shop.domain.model.Product;
import com.capi.shop.domain.repository.products.ProductRepository;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    PricingService pricingService;

    @Transactional
    @CacheResult(cacheName = "products")
    public List<ProductResponse> listProducts(int page, int size) {
        return productRepository
                .findAllPaginated(page, size)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ProductResponse toDto(Product p) {
        BigDecimal eff = pricingService.calculateEffectivePrice(p);
        return ProductResponse.from(p, eff);
    }
}
