// src/main/java/com/capi/shop/domain/model/Inventory.java
package com.capi.shop.domain.model;

/**
 * Current stock level for a given product.
 *
 * <p>Backed by its own table so we can track and update
 * inventory independently of product details.</p>
 *
 * @param id         surrogate database key
 * @param productId  the ID of the product
 * @param quantity   number of items available
 */
public record Inventory(
        Long id,
        Long productId,
        int quantity
) {}
