package com.microservice.inventory.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * @package : com.microservice.inventory.infrastructure.config
 * @name : OpenApiConfig.java
 * @date : 2025-20-04
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@OpenAPIDefinition(
        info = @Info(
                title = "APIs Linktic",
                description = "APIs para el manejo de Inventarios",
                termsOfService = "Licencia de uso de software estándar para Linktic",
                version = "1.0.0",
                contact = @Contact(
                        name = "Isaias Villarreal",
                        url = "wa.me/573116112594",
                        email = "isaiasvillarreal0225@mail.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Linktic",
                        url = "Url here"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "url"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "Url here"
                )
        }
        /*security = @SecurityRequirement(
                name = "Security Token"
        ) */
)
public @Configuration class OpenApiConfig {}
