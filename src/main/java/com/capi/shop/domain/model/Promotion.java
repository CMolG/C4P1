package com.capi.shop.domain.model;

import com.capi.shop.adapter.out.persistence.entity.PromotionEntity;

import java.math.BigDecimal;
import java.time.Instant;

public record Promotion(
        Long id,
        BigDecimal discountPercent,
        boolean active,
        Product product,
        Category category,
        String lastCharCoincidence,
        Instant startAtDate,
        Instant endAtDate
) {
    public Promotion(PromotionEntity e) {
        this(
                e.id,
                e.discountPercent,
                e.active,
                e.product  != null ? new Product(e.product)   : null,
                e.category != null ? new Category(e.category) : null,
                e.lastCharCoincidence,
                e.startAtDate,
                e.endAtDate
        );
    }

    public PromotionEntity toEntity() {
        var e = new PromotionEntity();
        e.id                 = this.id;
        e.discountPercent    = this.discountPercent;
        e.active             = this.active;
        if (this.product != null) {
            e.product = this.product.toEntity();
        }
        if (this.category != null) {
            e.category = this.category.toEntity();
        }
        e.lastCharCoincidence = this.lastCharCoincidence;
        e.startAtDate         = this.startAtDate;
        e.endAtDate           = this.endAtDate;
        return e;
    }
}
