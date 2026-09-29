package com.example.projet_5_safetynetspring_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// Personnalisation des éléments du swagger
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI safetyNetOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SafetyNet Alerts API")
                        .version("1.0")
                        .description(
                                "API REST permettant de gérer les données " +
                                        "et les alertes du système SafetyNet."
                        )
                );
    }
}