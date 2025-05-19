// src/main/java/com/capi/shop/domain/repository/PromotionRepository.java
package com.capi.shop.domain.repository;

import com.capi.shop.domain.model.Promotion;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/**
 * Port for CRUD operations on promotions.
 */
public interface PromotionRepository {
    Multi<Promotion> findActiveByProductId(Long productId);
    Uni<Promotion> save(Promotion promotion);
    Uni<Promotion> update(Promotion promotion);
}
