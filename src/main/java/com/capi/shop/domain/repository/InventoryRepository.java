// src/main/java/com/capi/shop/domain/repository/InventoryRepository.java
package com.capi.shop.domain.repository;

import com.capi.shop.domain.model.Inventory;
import io.smallrye.mutiny.Uni;

/**
 * Port for managing inventory levels.
 */
public interface InventoryRepository {
    Uni<Inventory> findByProductId(Long productId);
    Uni<Inventory> save(Inventory inventory);
    Uni<Inventory> update(Inventory inventory);
}
