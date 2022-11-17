package com.serverapp.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMovieRoutesConfig {
    @Bean
    public RouteLocator movieRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("movie", t -> t.path("/api/v1/movie").uri("lb://movie-service"))
                .route("wide", t -> t.path("/api/v1/movie/wide").uri("lb://movie-service"))
                .route("poster", t -> t.path("/api/v1/movie/poster").uri("lb://movie-service"))
                .build();
    }
}
