package com.capi.shop.domain.service;

import com.capi.shop.domain.model.Product;

import java.math.BigDecimal;

/**
 * A strategy for computing a discount percentage on a product.
 * Return a percentage (0–100).
 */
public interface DiscountPolicy {
    BigDecimal discountPercent(Product product);
}
