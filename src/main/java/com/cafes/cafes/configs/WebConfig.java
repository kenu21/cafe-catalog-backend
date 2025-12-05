package com.cafes.cafes.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${FRONTEND_LOCAL_PORT:81}")
    private String frontendPort;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String frontendOrigin = "http://localhost:" + frontendPort;
                registry.addMapping("/**")
                        .allowedOrigins(frontendOrigin)
                        .allowedMethods("*")
                        .allowCredentials(true);
            }
        };
    }
}
