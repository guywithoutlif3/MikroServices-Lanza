package ch.bbw.logisticprocessor.services;

import ch.bbw.logisticprocessor.model.ConfirmedOrder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderProducers {
    private KafkaTemplate<String, ConfirmedOrder> kafkaTemplate;
    public void sendMessage(ConfirmedOrder message) {
        kafkaTemplate.send("orderToProcess", message);
        System.out.println("message send");
    }
}