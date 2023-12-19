package ch.bbw.logisticprocessor.repositories;

import ch.bbw.logisticprocessor.model.ConfirmedOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<ConfirmedOrder, String> {
}
