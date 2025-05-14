package com.capi.shop.application.service;

import com.capi.shop.domain.model.Category;
import com.capi.shop.domain.model.Product;
import com.capi.shop.domain.model.Promotion;
import com.capi.shop.domain.repository.products.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricingServiceTest {

    @Mock
    PromotionRepository promotionRepo;

    @InjectMocks
    PricingService pricingService;

    private Product product;

    @BeforeEach
    void setUp() {
        Category cat = new Category(10L, "Test", "test", true);
        product = new Product(
                1L,
                "SKU1235",
                "Test Product",
                cat,
                BigDecimal.valueOf(200.00),
                Instant.now()
        );
    }

    @Test
    void calculateEffectivePrice_picksHighestApplicableDiscount() {
        Instant now = Instant.now();

        Promotion p1 = new Promotion(
                1L, BigDecimal.valueOf(25), true,
                product,      // product-specific
                null,
                null,
                now.minusSeconds(10),
                now.plusSeconds(10)
        );
        Promotion p2 = new Promotion(
                2L, BigDecimal.valueOf(20), true,
                null,
                product.category(),  // category-wide
                null,
                now.minusSeconds(10),
                now.plusSeconds(10)
        );
        Promotion p3 = new Promotion(
                3L, BigDecimal.valueOf(30), true,
                null,
                null,
                "5",  // suffix-based
                now.minusSeconds(10),
                now.plusSeconds(10)
        );
        Promotion p4 = new Promotion(
                4L, BigDecimal.valueOf(15), true,
                null,
                null,
                null,
                now.minusSeconds(10),
                now.plusSeconds(10)
        );

        List<Promotion> promos = List.of(p1, p2, p3, p4);
        when(promotionRepo.findAll()).thenReturn(promos);

        BigDecimal effective = pricingService.calculateEffectivePrice(product);

        assertEquals(0, BigDecimal.valueOf(140.00).compareTo(effective));
    }

    @Test
    void calculateEffectivePrice_filtersInactiveAndOutOfWindow() {
        Instant now = Instant.now();

        Promotion expired = new Promotion(
                1L, BigDecimal.valueOf(50), true,
                null, null, null,
                now.minusSeconds(20),
                now.minusSeconds(10)
        );
        Promotion inactive = new Promotion(
                2L, BigDecimal.valueOf(40), false,
                null,
                null,
                "5",
                now.minusSeconds(10),
                now.plusSeconds(10)
        );
        Promotion validSuffix = new Promotion(
                3L, BigDecimal.valueOf(10), true,
                null,
                null,
                "5",
                now.minusSeconds(10),
                now.plusSeconds(10)
        );

        List<Promotion> promos = List.of(expired, inactive, validSuffix);
        when(promotionRepo.findAll()).thenReturn(promos);

        BigDecimal effective = pricingService.calculateEffectivePrice(product);

        assertEquals(0, BigDecimal.valueOf(180.00).compareTo(effective));
    }
}
