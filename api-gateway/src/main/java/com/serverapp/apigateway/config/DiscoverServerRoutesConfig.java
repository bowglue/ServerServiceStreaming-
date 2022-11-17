package com.serverapp.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscoverServerRoutesConfig {
    @Bean
    public RouteLocator discoveryRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("discovery-server", t -> t.path("/eureka/web")
                        .filters(rw -> rw.setPath("/"))
                        .uri("http://localhost:8761"))
                .route("static-resources", t -> t.path("/eureka/**").uri("http://localhost:8761"))
                .build();
    }
}
