package com.capi.shop.application.dto;

import com.capi.shop.domain.model.Product;

import java.math.BigDecimal;

public record ProductResponse(
        Long      id,
        String    sku,
        String    description,
        String    categoryJsonId,
        String    categoryName,
        BigDecimal basePrice,
        BigDecimal effectivePrice
) {
    public static ProductResponse from(Product product, BigDecimal effectivePrice) {
        return new ProductResponse(
                product.id(),
                product.sku(),
                product.description(),
                product.category().jsonId(),
                product.category().name(),
                product.basePrice(),
                effectivePrice
        );
    }
}
