package ch.bbw.product_catalog.controller;

import ch.bbw.product_catalog.dto.PlaceOrderDto;
import ch.bbw.product_catalog.exceptions.UserNotFoundException;
import ch.bbw.product_catalog.model.OrderPlacement;
import ch.bbw.product_catalog.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderPlacement> placeOrder(@RequestBody PlaceOrderDto order){
        orderService.placeOrder(order);
        return null;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
