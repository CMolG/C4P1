// src/main/java/com/capi/shop/domain/repository/ProductRepository.java
package com.capi.shop.domain.repository;

import com.capi.shop.domain.model.Product;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/**
 * Port for CRUD operations on products.
 */
public interface ProductRepository {
    Uni<Product> findById(Long id);
    Multi<Product> findAll();
    Uni<Product> save(Product product);
    Uni<Product> update(Product product);
}
