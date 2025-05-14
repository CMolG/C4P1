package com.capi.shop.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Core product entity.
 *
 * <p>Each product belongs to exactly one {@link Category}. When
 * serializing to JSON, clients will see the category's {@code jsonId}
 * (e.g. "electronics") to filter/sort, and the human name for display.
 * </p>
 *
 * @param id          surrogate database key
 * @param sku         unique stock-keeping unit
 * @param description short text description
 * @param category    the product's category (may be disabled)
 * @param basePrice   original price before any discount
 * @param createdAt   timestamp when this product was first created
 */
public record Product(
        Long id,
        String sku,
        String description,
        Category category,
        BigDecimal basePrice,
        Instant createdAt
) {}
