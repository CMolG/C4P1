package com.capi.shop.domain.model;

/**
 * A product category.
 *
 * <p>This is a first-class entity (backed by its own table) so that:
 * <ul>
 *   <li>We can add or remove categories without a recompile.</li>
 *   <li>Each category has a stable JSON identifier for clients to filter on.</li>
 *   <li>We can disable a category (enabled=false) to hide all its products at once.</li>
 * </ul>
 * </p>
 *
 * @param id       surrogate database key
 * @param name     human-readable display name
 * @param jsonId   stable identifier used in JSON APIs (e.g. "electronics", "home_kitchen")
 * @param enabled  whether this category is active; inactive ones should be hidden
 */
public record Category(
        Long id,
        String name,
        String jsonId,
        boolean enabled
) {}
