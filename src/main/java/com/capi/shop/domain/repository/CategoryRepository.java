package com.capi.shop.domain.repository;

import com.capi.shop.domain.model.Inventory;
import io.smallrye.mutiny.Uni;

/**
 * Port for managing category levels.
 */
public interface CategoryRepository {
    Uni<Inventory> findByProductId(Long productId);
    Uni<Inventory> save(Inventory inventory);
    Uni<Inventory> update(Inventory inventory);
}
