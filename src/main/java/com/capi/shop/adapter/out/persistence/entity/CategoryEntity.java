package com.capi.shop.adapter.out.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends PanacheEntity {

    @Column(name = "name", nullable = false, length = 100)
    public String name;

    @Column(name = "json_id", nullable = false, unique = true, length = 50)
    public String jsonId;

    @Column(name = "enabled", nullable = false)
    public boolean enabled;
}
