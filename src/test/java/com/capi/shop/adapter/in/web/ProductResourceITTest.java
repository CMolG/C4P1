package com.capi.shop.adapter.in.web;

import com.capi.shop.test.PostgreSQLTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(PostgreSQLTestResource.class)
public class ProductResourceITTest {

    @Test
    void listAllProducts_andCheckEffectivePrice() {
        RestAssured.basePath = "/products";

        JsonPath json = given()
                .when().get()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].effectivePrice", notNullValue())
                .extract().jsonPath();

        List<Float> basePrices      = json.getList("basePrice", Float.class);
        List<Float> effectivePrices = json.getList("effectivePrice", Float.class);

        assertEquals(basePrices.size(), effectivePrices.size(),
                "Debe haber igual número de basePrice y effectivePrice");

        for (int i = 0; i < basePrices.size(); i++) {
            float bp = basePrices.get(i);
            float ep = effectivePrices.get(i);
            assertTrue(ep <= bp,
                    String.format("El producto %d tiene effectivePrice %f > basePrice %f", i, ep, bp));
        }
    }

    @Test
    void filterByCategory_andSortBySku() {
        RestAssured.basePath = "/products";

        given()
                .queryParam("category", "electronics")
                .queryParam("sortBy", "sku")
                .when().get()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("sku", everyItem(startsWithIgnoringCase("SKU")))
                .body("sku", is(sortedAscending()));
    }

    private static org.hamcrest.Matcher<Iterable<String>> sortedAscending() {
        return new org.hamcrest.TypeSafeDiagnosingMatcher<Iterable<String>>() {
            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("una lista ordenada ascendentemente");
            }
            @Override
            protected boolean matchesSafely(Iterable<String> items, org.hamcrest.Description mismatch) {
                String prev = null;
                for (String current : items) {
                    if (prev != null && prev.compareTo(current) > 0) {
                        mismatch.appendText("encontrado ").appendValue(current)
                                .appendText(" después de ").appendValue(prev);
                        return false;
                    }
                    prev = current;
                }
                return true;
            }
        };
    }
}
