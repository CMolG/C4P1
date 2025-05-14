package com.capi.shop.adapter.out.persistence.impl;

import com.capi.shop.adapter.out.persistence.ProductEntityRepository;
import com.capi.shop.adapter.out.persistence.entity.ProductEntity;
import com.capi.shop.domain.model.Product;
import com.capi.shop.domain.repository.products.ProductRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    ProductEntityRepository productEntityRepository;

    @Override
    public List<Product> findAll() {
        return productEntityRepository
                .listAll()
                .stream()
                .map(Product::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllPaginated(int pageIndex, int pageSize) {
        Page page = Page.of(pageIndex - 1, pageSize);

        PanacheQuery<ProductEntity> query = productEntityRepository.findAll();
        query.page(page);

        return query
                .list()
                .stream()
                .map(Product::new)
                .collect(Collectors.toList());
    }
}
