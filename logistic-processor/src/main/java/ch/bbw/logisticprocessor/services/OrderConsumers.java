package ch.bbw.logisticprocessor.services;

import ch.bbw.logisticprocessor.model.ConfirmedOrder;
import ch.bbw.logisticprocessor.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderConsumers {
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "orderToProcess", groupId = "order")
    public void listen(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ConfirmedOrder confirmedOrder = objectMapper.readValue(message, ConfirmedOrder.class);
            log.info("Order to process: " + confirmedOrder.toString());
            orderRepository.save(confirmedOrder);
        } catch (JsonProcessingException e) {
            log.error("Error processing message", e);
        }
    }

}
