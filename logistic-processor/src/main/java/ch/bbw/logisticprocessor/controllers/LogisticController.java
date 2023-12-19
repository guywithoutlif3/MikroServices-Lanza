package ch.bbw.logisticprocessor.controllers;

import ch.bbw.logisticprocessor.model.ConfirmedOrder;
import ch.bbw.logisticprocessor.services.OrderProducers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registerorder")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LogisticController {
    private OrderProducers orderProducers;
    @PostMapping
    public ResponseEntity<String> processOrder(@RequestBody ConfirmedOrder order){
        System.out.println("GOT REQUEST");
        log.info(order.toString());
        orderProducers.sendMessage(order);
        return ResponseEntity.ok("Order processed");
    }
}
