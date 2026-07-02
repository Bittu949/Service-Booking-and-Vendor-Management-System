package com.sqts.sbvms.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Service Booking Vendor Management System API")
                                .version("1.0")
                                .description(
                                        "REST API for managing authentication, vendors, services and bookings."
                                )
                                .contact(
                                        new Contact()
                                                .name("Balkrishna Naik")
                                                .email("naikbalkrishna296@gmail.com")
                                )
                )
                .addTagsItem(new Tag()
                        .name("1. 🔐 Authentication")
                        .description("Public endpoints for customer registration, vendor registration, and user authentication."))
                .addTagsItem(new Tag()
                        .name("2. 👤 Customer APIs")
                        .description("Operations available to authenticated customers for browsing services and managing bookings."))
                .addTagsItem(new Tag()
                        .name("3. 🛠 Vendor APIs")
                        .description("Operations available to authenticated vendors for managing profiles, services, and assigned bookings."))
                .addTagsItem(new Tag()
                        .name("4. 👨‍💼 Administrator APIs")
                        .description("Administrative operations for managing vendors, service categories, bookings, and system resources."))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer Authentication",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
