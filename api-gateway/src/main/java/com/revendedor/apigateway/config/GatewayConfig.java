package com.revendedor.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@Configuration
public class GatewayConfig {
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ticket-service", r -> r.path("/ticket-service/**")
                        .filters(f -> {
                            f.stripPrefix(1);
                            //f.filter(filter);
                            return f;
                        })
                        .uri("lb://ticket-service/"))
                .route("user-service", r -> r.path("/user-service/**")
                        .filters(f -> {
                            f.stripPrefix(1);
                            //f.filter(filter);
                            return f;
                        })
                        .uri("lb://user-service/"))
                .build();
    }
}
