package com.capi.shop.adapter.out.persistence;

import com.capi.shop.adapter.out.persistence.entity.CategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Panache repository for {@link CategoryEntity}.
 */
@ApplicationScoped
public class CategoryEntityRepository implements PanacheRepository<CategoryEntity> {
}
