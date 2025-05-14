package com.capi.shop.adapter.out.persistence;

import com.capi.shop.adapter.out.persistence.entity.PromotionEntity;
import com.capi.shop.adapter.out.persistence.impl.PromotionRepositoryImpl;
import com.capi.shop.domain.model.Promotion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PromotionRepositoryImplTest {

    @Mock
    PromotionEntityRepository repo;

    @InjectMocks
    PromotionRepositoryImpl impl;

    @Test
    void findAll_returnsAllMapped() {
        PromotionEntity e1 = new PromotionEntity();
        e1.id = 1L; e1.discountPercent = BigDecimal.ONE; e1.active = true;
        PromotionEntity e2 = new PromotionEntity();
        e2.id = 2L; e2.discountPercent = BigDecimal.TEN; e2.active = false;

        when(repo.listAll()).thenReturn(List.of(e1, e2));

        List<Promotion> list = impl.findAll();

        assertEquals(2, list.size());
        assertTrue(list.stream().map(Promotion::id).collect(Collectors.toSet())
                .containsAll(List.of(1L, 2L)));

        verify(repo).listAll();
    }
}
