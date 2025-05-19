// src/main/java/com/capi/shop/domain/service/PricingService.java
package com.capi.shop.domain.service;

import io.smallrye.mutiny.Uni;
import java.math.BigDecimal;

/**
 * Domain service for calculating effective prices,
 * taking into account any active promotions.
 */
public interface PricingService {
    /**
     * Calculate the price for a given product ID,
     * applying the best current promotion if available.
     */
    Uni<BigDecimal> calculateEffectivePrice(Long productId);
}
