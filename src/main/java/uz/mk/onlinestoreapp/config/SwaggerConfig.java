package uz.mk.onlinestoreapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${appVersion}")
    private String appVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                .scheme("bearer").in(SecurityScheme.In.HEADER).name("Authorization"))
                )
                .info(new Info().title("Online Shopping Service").description(
                        "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.").version(appVersion))
                .openapi("3.0.2")

                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
                ;
    }

}
