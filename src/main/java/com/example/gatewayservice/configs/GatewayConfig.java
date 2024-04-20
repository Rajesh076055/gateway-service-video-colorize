package com.example.gatewayservice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/graphql")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .route("video-ops-service", r -> r.path("/video-ops/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://video-ops-service"))
                .build();
    }
}
