package com.odk3.vetcare.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiVetCareOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("VetCare Api")
                        .description("Une api VetCare pour les plateformes de mises en relation entre Clients et Vétérinaire")
                        .version("1.0.0"));
    }
}



