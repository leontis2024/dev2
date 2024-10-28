package com.example.leontis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // permite CORS para todas as rotas
                        .allowedOrigins("https://arearestrita.onrender.com") // Substitua pelo seu domínio
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
