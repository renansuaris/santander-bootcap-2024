package com.dio.santander_bootcap_2024.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Football API",
                description = "This API provides endpoints to manage players, clubs, and leagues in the world of football.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Renan Soares",
                        email = "renansoaresdev@gmail.com"
                )
        )
)

public class OpenApiConfiguration {

}
