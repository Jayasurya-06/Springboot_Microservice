package com.jk.microservices.product.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productServiceApi(){
        return new OpenAPI().info(new Info()
                .title("Product Service API")
                .description("REST API for product service")
                .license(new License().name("Apache 2.0"))
                ).externalDocs(new ExternalDocumentation()
                .description("You can refer to the product service Wiki Documentation")
                .url("http://product-service"));
    }

}
