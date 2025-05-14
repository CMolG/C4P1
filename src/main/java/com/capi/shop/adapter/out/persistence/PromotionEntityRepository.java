package com.capi.shop.adapter.out.persistence;

import com.capi.shop.adapter.out.persistence.entity.PromotionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Panache repository for {@link PromotionEntity}.
 */
@ApplicationScoped
public class PromotionEntityRepository implements PanacheRepository<PromotionEntity> {
}
