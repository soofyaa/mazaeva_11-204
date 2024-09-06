package ru.itis.animerec.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI().info(apiInfo());
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    private Info apiInfo() {
        return new Info()
                .title("Anime Recommender API")
                .description("API Documentation for Anime Recommender")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Mazaeva Sofya")
                        .url("-")
                        .email("SVMazaeva@stud.kpfu.ru"));
    }
}