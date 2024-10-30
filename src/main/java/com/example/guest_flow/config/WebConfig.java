package com.example.guest_flow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://127.0.0.1:3000"); // Set allowed origin (without trailing slash)
        config.addAllowedMethod("*"); // Allow any HTTP method
        config.addAllowedHeader("*"); // Allow any headers
        config.setAllowCredentials(true); // Allow credentials, if needed

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply this configuration to all endpoints

        return new CorsFilter(source); // Pass the source to CorsFilter
    }
}
