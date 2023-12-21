package ch.bbw.product_catalog.service;


import ch.bbw.product_catalog.dto.LogisticOrderDto;
import ch.bbw.product_catalog.dto.PlaceOrderDto;
import ch.bbw.product_catalog.exceptions.ProductNotFoundException;
import ch.bbw.product_catalog.exceptions.UserNotFoundException;
import ch.bbw.product_catalog.model.Customer;
import ch.bbw.product_catalog.model.OrderPlacement;
import ch.bbw.product_catalog.model.Product;
import ch.bbw.product_catalog.repository.OrderRepository;
import ch.bbw.product_catalog.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.time.LocalDate;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductRepository productRepository;
    private WebClient.Builder webClientBuilder;

    @Transactional
    public void placeOrder(PlaceOrderDto orderDto){
        Customer customer = customerService.findCustomer(orderDto.getCustomerUsername());
        if (customer != null){
            OrderPlacement confirmedOrder = new OrderPlacement(Date.valueOf(LocalDate.now()), customer);
            orderDto.getProducts().forEach(product -> {
                Product confirmedProduct = productRepository.findById(product.getProductId())
                        .orElseThrow(() -> new ProductNotFoundException("Product: " + product.getName() + " with the id: "
                                + product.getProductId() + " not found"));
                confirmedOrder.getProducts().add(confirmedProduct);
            });

            orderRepository.save(confirmedOrder);
            callLogisticsService(new LogisticOrderDto(
                    confirmedOrder.getCustomer().getFirstname(),
                    confirmedOrder.getCustomer().getAddress(),
                    confirmedOrder.getCustomer().getLastname(),
                    confirmedOrder.getCustomer().getPostalCode(),
                    orderDto.getProducts(),
                    confirmedOrder.getDate()
            ));

        } else {
            throw new UserNotFoundException("User: " + orderDto.getCustomerUsername() + " was not found in the database");
        }
    }

    private void callLogisticsService(LogisticOrderDto logisticOrderDto) {
        webClientBuilder.build()
                .post()
                .uri("http://logistic-processor:8080/registerorder")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(logisticOrderDto), LogisticOrderDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
