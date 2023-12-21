package ch.bbw.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-catalog", r -> r.path("/product-catalog/**")
                        .uri("http://localhost:8080/"))
                .route("logistic-processor", r -> r.path("/logistic-processor/**")
                        .uri("http://localhost:8083/"))
                .route("shopping-cart", r -> r.path("/shopping-cart/**")
                        .uri("http://localhost:8082/"))
                .build();
    }
}
