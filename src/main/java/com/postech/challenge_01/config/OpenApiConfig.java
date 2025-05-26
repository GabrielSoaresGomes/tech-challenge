package com.postech.challenge_01.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tech Challenge 1 - Gerenciamento de usuários, endereços e autenticação de usuários")
                        .description("Esta API RESTful permite buscar, adicionar, atualizar e deletar os usuários e endereços, também permite a autenticação de usuários.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Uso acadêmico - Tech Challenge")
                                .url("https://github.com/GabrielSoaresGomes/tech-challenge-01")
                        )
                );
    }
}