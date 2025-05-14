package com.capi.shop.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * A discount rule that can be applied in exactly one of four mutually‐exclusive ways:
 * <ul>
 *   <li><b>Product‐specific</b>: when {@code productId} is non‐null.</li>
 *   <li><b>Category‐wide</b>: when {@code categoryId} is non‐null.</li>
 *   <li><b>Suffix‐based</b>: when {@code lastCharCoincidence} is non‐null (matches end of SKU).</li>
 *   <li><b>Time‐window</b>: when both {@code startAtDate} and {@code endAtDate} are non‐null.</li>
 * </ul>
 * <p>Exactly one of these must be set, and {@code active} must be {@code true} for the rule to apply.</p>
 *
 * @param id                    surrogate database key
 * @param discountPercent       percentage discount to apply (0..100)
 * @param active                whether this rule is currently enabled
 * @param productId             if non‐null, this is a product‐specific discount
 * @param categoryId            if non‐null, this applies to all products in that category
 * @param lastCharCoincidence   if non‐null, applies to products whose SKU ends with these chars
 * @param startAtDate           the beginning date of the promotion (inclusive)
 * @param endAtDate             the end date of the promotion (inclusive)
 */
public record Promotion(
        Long id,
        BigDecimal discountPercent,
        boolean active,
        Long productId,
        Long categoryId,
        String lastCharCoincidence,
        Instant startAtDate,
        Instant endAtDate
) {
    /**
     * Validates that exactly one promotion rule type is set.
     *
     * @return {@code true} if exactly one of productId, categoryId,
     *         lastCharCoincidence or (startAtDate & endAtDate) is non‐null.
     */
    public boolean isValidRule() {
        int count = 0;
        if (productId != null) count++;
        if (categoryId != null) count++;
        if (lastCharCoincidence != null && !lastCharCoincidence.isEmpty()) count++;
        return count == 1;
    }
}
