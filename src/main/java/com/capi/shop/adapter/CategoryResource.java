package com.capi.shop.adapter;

import com.capi.shop.domain.model.Category;
import com.capi.shop.domain.repository.CategoryRepository;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryRepository categoryRepo;

    /**
     * List categories.
     * @param onlyEnabled if true, returns only enabled categories; default true.
     */
    @GET
    public Multi<CategoryResponse> list(
            @RestQuery(defaultValue = "true") boolean onlyEnabled
    ) {
        return categoryRepo.findAll(onlyEnabled)
                .map(cat -> new CategoryResponse(
                        cat.jsonId(),
                        cat.name(),
                        cat.enabled()
                ));
    }

    /** DTO returned by GET /categories */
    public record CategoryResponse(
            String jsonId,
            String name,
            boolean enabled
    ) {}
}
