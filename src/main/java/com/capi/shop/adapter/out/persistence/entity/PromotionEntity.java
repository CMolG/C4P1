package com.capi.shop.adapter.out.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "promotions")
public class PromotionEntity extends PanacheEntity {

    @Column(name = "discount_percent", nullable = false)
    public BigDecimal discountPercent;

    @Column(name = "active", nullable = false)
    public boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_promo_prod"))
    public ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_promo_cat"))
    public CategoryEntity category;

    @Column(name = "last_char_coincidence", length = 10)
    public String lastCharCoincidence;

    @Column(name = "start_at_date", nullable = false)
    public Instant startAtDate;

    @Column(name = "end_at_date", nullable = false)
    public Instant endAtDate;
}
