package com.capi.shop.domain.repository;

import java.util.List;

/**
 * Generic repository. It contains generic Read methods.
 *
 * @param <T>  Entity type.
 */
public interface ReadRepository<T> {
    List<T> findAll();
}
