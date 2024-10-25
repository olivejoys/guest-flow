package com.example.guest_flow.config;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig {


        @Bean
        public CorsFilter corsFilter() {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("http://127.0.0.1:3000"); // Set allowed origins
            config.addAllowedMethod("*"); // Allow any HTTP method
            config.addAllowedHeader("*"); // Allow any headers
            config.setAllowCredentials(true); // Allow credentials, if needed

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config); // Apply this configuration to all endpoints

            return new CorsFilter();
        }
    }

