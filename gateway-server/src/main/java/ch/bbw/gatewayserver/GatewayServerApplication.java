package ch.bbw.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@Configuration
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-catalog", r -> r.path("/product-catalog/**")
                        .filters(f -> f.rewritePath("/product-catalog/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8080/"))
                .route("logistic-processor", r -> r.path("/logistic-processor/**")
                        .filters(f -> f.rewritePath("/logistic-processor/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8083/"))
                .route("shopping-cart", r -> r.path("/shopping-cart/**")
                        .filters(f -> f.rewritePath("/shopping-cart/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8082/"))
                .build();
    }

}
