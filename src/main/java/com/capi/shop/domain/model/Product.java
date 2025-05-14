package com.capi.shop.domain.model;

import com.capi.shop.adapter.out.persistence.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.Instant;

public record Product(
        Long id,
        String sku,
        String description,
        Category category,
        BigDecimal basePrice,
        Instant createdAt
) {
    public Product(ProductEntity e) {
        this(
                e.id,
                e.sku,
                e.description,
                new Category(e.category),
                e.basePrice,
                e.createdAt
        );
    }

    public ProductEntity toEntity() {
        var e = new ProductEntity();
        e.id          = this.id;
        e.sku         = this.sku;
        e.description = this.description;
        e.category    = this.category.toEntity();
        e.basePrice   = this.basePrice;
        e.createdAt   = this.createdAt;
        return e;
    }
}
