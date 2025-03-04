package com.jk.microservices.order.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderServiceApi(){
        return new OpenAPI().info(new Info()
                .title("Order Service API")
                .description("REST API for order service")
                .license(new License().name("Apache 2.0"))
                ).externalDocs(new ExternalDocumentation()
                .description("You can refer to the order service Wiki Documentation")
                .url("http://product-service"));
    }

}
