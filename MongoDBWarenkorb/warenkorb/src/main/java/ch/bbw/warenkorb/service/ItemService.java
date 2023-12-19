package ch.bbw.warenkorb.service;

import ch.bbw.warenkorb.Item;
import ch.bbw.warenkorb.dto.PlaceOrderDto;
import ch.bbw.warenkorb.repositories.ItemsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ItemService {
    private final ItemsRepository itemsRepository;
    private WebClient.Builder webClientBuilder;

    public Mono<PlaceOrderDto> placeOrderByCart(String customerUsername){
        log.info(customerUsername);
        List<Item> itemsToOrder = itemsRepository.findItemsByCustomerUsername(customerUsername);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(customerUsername, itemsToOrder);
        return callProductCatalog(placeOrderDto);
    }

    private Mono<PlaceOrderDto> callProductCatalog(PlaceOrderDto placeOrderDto) {
        return webClientBuilder.build()
                .post()
                .uri("http://product-catalog:8080/order")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(placeOrderDto), PlaceOrderDto.class)
                .retrieve()
                .bodyToMono(PlaceOrderDto.class)
                .onErrorResume(e -> {
                    // Error handling logic here
                    log.error(e.getMessage());
                    throw new RuntimeException(e.getMessage());
                });
    }

}
