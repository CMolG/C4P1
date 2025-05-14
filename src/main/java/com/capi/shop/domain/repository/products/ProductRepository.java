package com.capi.shop.domain.repository.products;

import com.capi.shop.domain.model.Product;
import com.capi.shop.domain.repository.ReadRepository;

import java.util.List;

public interface ProductRepository extends ReadRepository<Product> {
    List<Product> findAllPaginated(int pageIndex, int pageSize);
}
