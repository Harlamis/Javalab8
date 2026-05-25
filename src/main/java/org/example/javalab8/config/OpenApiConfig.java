package org.example.javalab8.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("").description("Default Server")))
                .info(new Info()
                        .title("Java Lab 8/10 API") // 7a. Назва продукту
                        .description("REST API demo") // 7b. Опис
                        .version("0.0.9")
                        .contact(new Contact()
                                .name("Danyliuk Yeghor")
                                .email("danyliuk.yehor@chnu.edu.ua")
                                .url("https://github.com/Harlamis"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}