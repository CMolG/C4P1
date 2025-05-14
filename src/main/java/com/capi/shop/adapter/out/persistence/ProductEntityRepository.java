package com.capi.shop.adapter.out.persistence;

import com.capi.shop.adapter.out.persistence.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Panache repository for {@link ProductEntity}.
 */
@ApplicationScoped
public class ProductEntityRepository implements PanacheRepository<ProductEntity> {
}
