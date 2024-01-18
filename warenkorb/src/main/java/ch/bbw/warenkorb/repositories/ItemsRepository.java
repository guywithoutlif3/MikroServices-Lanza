package ch.bbw.warenkorb.repositories;

import ch.bbw.warenkorb.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends MongoRepository<Item, String> {
    List<Item> findItemsByCustomerUsername(String customerUsername);
}
