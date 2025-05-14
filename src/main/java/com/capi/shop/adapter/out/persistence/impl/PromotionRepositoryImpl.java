package com.capi.shop.adapter.out.persistence.impl;

import com.capi.shop.adapter.out.persistence.PromotionEntityRepository;
import com.capi.shop.domain.model.Promotion;
import com.capi.shop.domain.repository.products.PromotionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PromotionRepositoryImpl implements PromotionRepository {

    @Inject
    PromotionEntityRepository repo;

    @Override
    public List<Promotion> findAll() {
        return repo.listAll().stream()
                .map(Promotion::new)
                .collect(Collectors.toList());
    }
}
