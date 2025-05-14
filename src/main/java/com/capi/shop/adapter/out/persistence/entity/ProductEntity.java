package com.capi.shop.adapter.out.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "products")
public class ProductEntity extends PanacheEntity {

    @Column(name = "sku", nullable = false, unique = true, length = 20)
    public String sku;

    @Column(name = "description")
    public String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_prod_cat"))
    public CategoryEntity category;

    @Column(name = "base_price", nullable = false)
    public BigDecimal basePrice;

    @Column(name = "created_at", nullable = false)
    public Instant createdAt;
}
