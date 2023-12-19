package ch.bbw.logisticprocessor.services;

import ch.bbw.logisticprocessor.model.ConfirmedOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderProducers {
    private KafkaTemplate<String, ConfirmedOrder> kafkaTemplate;
    public void sendMessage(ConfirmedOrder message) {
        message.setDate(message.getDate());
        kafkaTemplate.send("orderToProcess", message);
        System.out.println("message send");
    }

    public void sendMessageDelete(ConfirmedOrder message) {
        kafkaTemplate.send("orderProcessed", message);
        log.info("Message send to delete: " + message.toString());
    }
}