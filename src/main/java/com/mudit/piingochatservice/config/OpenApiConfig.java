package com.mudit.piingochatservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI chatServiceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Piingo Chat Service API")
                        .description("API documentation for Piingo chat-service endpoints.")
                        .version("v1")
                        .contact(new Contact().name("Piingo")));
    }
}