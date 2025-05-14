package com.capi.shop.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

/**
 * Starts a PostgreSQL container for Quarkus @QuarkusTest
 * and sets both JDBC and reactive URLs into Quarkus config.
 */
public class PostgreSQLTestResource implements QuarkusTestResourceLifecycleManager {

    private static final PostgreSQLContainer<?> PG =
            new PostgreSQLContainer<>("postgres:14-alpine")
                    .withDatabaseName("capi-shop")
                    .withUsername("capi-user")
                    .withPassword("capi-pass");

    @Override
    public Map<String, String> start() {
        PG.start();
        String reactiveUrl = String.format("postgresql://%s:%d/%s",
                PG.getHost(),
                PG.getFirstMappedPort(),
                PG.getDatabaseName());

        return Map.of(
                "quarkus.datasource.jdbc.url",    PG.getJdbcUrl(),
                "quarkus.datasource.username",    PG.getUsername(),
                "quarkus.datasource.password",    PG.getPassword(),
                "quarkus.datasource.reactive.url",      reactiveUrl,
                "quarkus.datasource.reactive.username", PG.getUsername(),
                "quarkus.datasource.reactive.password", PG.getPassword()
        );
    }

    @Override
    public void stop() {
        PG.stop();
    }
}
