package com.capi.shop.domain.model;

import com.capi.shop.adapter.out.persistence.entity.CategoryEntity;

public record Category(
        Long id,
        String name,
        String jsonId,
        boolean enabled
) {
    public Category(CategoryEntity e) {
        this(e.id, e.name, e.jsonId, e.enabled);
    }

    public CategoryEntity toEntity() {
        var e = new CategoryEntity();
        e.id      = this.id;
        e.name    = this.name;
        e.jsonId  = this.jsonId;
        e.enabled = this.enabled;
        return e;
    }
}
