package com.tienda.ecomerce1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce API")
                        .version("1.0")
                        .description("API documentation for the e-commerce application")
                        .contact(new Contact()
                                .name("E-comerce Support")
                                .email("support@tienda.com")
                                .url("https://www.tienda.com")));
    }
}
