package com.capi.shop.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

/**
 * Basic OpenAPI metadata for Swagger UI / codegen.
 *
 * This will be picked up by Quarkus and exposed on /q/openapi and /q/swagger-ui.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Capi Shop API",
                version = "1.0.0",
                description = "Reactive store management API with SSE updates",
                contact = @Contact(
                        name = "Capi Shop Team",
                        email = "support@capi-shop.com"
                ),
                license = @License(
                        name = "MIT-License",
                        url = "https://mit-license.org/"
                )
        )
)
public class OpenAPIConfig {

}
