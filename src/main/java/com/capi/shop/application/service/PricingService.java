package com.capi.shop.application.service;

import com.capi.shop.domain.model.Product;
import com.capi.shop.domain.model.Promotion;
import com.capi.shop.domain.repository.products.PromotionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class PricingService {

    @Inject
    PromotionRepository promotionRepo;

    public BigDecimal calculateEffectivePrice(Product product) {
        Instant now = Instant.now();

        List<Promotion> allPromos = promotionRepo.findAll();

        BigDecimal bestDiscount = allPromos.stream()
                .filter(Promotion::active)
                .filter(p -> !p.startAtDate().isAfter(now) && !p.endAtDate().isBefore(now))
                .filter(p -> matchesRule(p, product))
                .map(Promotion::discountPercent)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal discountAmount = product.basePrice()
                .multiply(bestDiscount)
                .divide(BigDecimal.valueOf(100));

        return product.basePrice().subtract(discountAmount);
    }

    private boolean matchesRule(Promotion promo, Product product) {
        if (promo.product() != null) {
            return promo.product().id().equals(product.id());
        }

        if (promo.category() != null) {
            return promo.category().id().equals(product.category().id());
        }

        if (promo.lastCharCoincidence() != null) {
            return product.sku().endsWith(promo.lastCharCoincidence());
        }

        return false;
    }
}
