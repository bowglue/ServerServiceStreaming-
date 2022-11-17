package com.serverapp.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebTrailerRoutesConfig {
    @Bean
    public RouteLocator trailerRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("video", t -> t.path("/api/v1/video").uri("lb://trailer-service"))
                .route("stream", t -> t.path("/api/v1/video/**")
                        .filters(rw -> rw.rewritePath("/api/v1/video/(?<name>.*)", "/api/v1/video/${name}"))
                        .uri("lb://trailer-service"))
                .build();
    }
}
