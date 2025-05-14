package com.capi.shop.adapter.out.persistence;

import com.capi.shop.adapter.out.persistence.entity.CategoryEntity;
import com.capi.shop.adapter.out.persistence.entity.ProductEntity;
import com.capi.shop.adapter.out.persistence.impl.ProductRepositoryImpl;
import com.capi.shop.domain.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @Mock
    ProductEntityRepository repo;

    @Mock
    PanacheQuery<ProductEntity> panacheQuery;

    @InjectMocks
    ProductRepositoryImpl impl;

    private ProductEntity e1;
    private ProductEntity e2;

    @BeforeEach
    void setUp() {
        CategoryEntity cat = new CategoryEntity();
        cat.id = 10L;
        cat.jsonId = "test";
        cat.name = "Test";
        cat.enabled = true;

        e1 = new ProductEntity();
        e1.id = 1L; e1.sku = "B"; e1.description = "descB"; e1.category = cat;
        e1.basePrice = BigDecimal.ONE; e1.createdAt = Instant.now();

        e2 = new ProductEntity();
        e2.id = 2L; e2.sku = "A"; e2.description = "descA"; e2.category = cat;
        e2.basePrice = BigDecimal.TEN; e2.createdAt = Instant.now();
    }

    @Test
    void findAll_returnsAllMapped() {
        when(repo.listAll()).thenReturn(List.of(e1, e2));

        List<Product> list = impl.findAll();

        assertEquals(2, list.size());
        assertTrue(list.stream().anyMatch(p -> p.id().equals(1L) && p.sku().equals("B")));
        assertTrue(list.stream().anyMatch(p -> p.id().equals(2L) && p.sku().equals("A")));

        verify(repo).listAll();
    }

    @Test
    void findAllPaginated_appliesPageAndMaps() {
        when(repo.findAll()).thenReturn(panacheQuery);
        when(panacheQuery.page(any(Page.class))).thenReturn(panacheQuery);
        when(panacheQuery.list()).thenReturn(List.of(e1, e2));

        List<Product> result = impl.findAllPaginated(3, 3);

        assertEquals(2, result.size());
        assertEquals("B", result.get(0).sku());
        assertEquals("A", result.get(1).sku());

        verify(repo).findAll();

        ArgumentCaptor<Page> captor = ArgumentCaptor.forClass(Page.class);
        verify(panacheQuery).page(captor.capture());
        assertNotNull(captor.getValue(), "Page object should not be null");

        verify(panacheQuery).list();
    }
}
