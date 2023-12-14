package ch.bbw.warenkorb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // Lombok getter und Setter
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String text;
    private double price;
    private String size;
    private int rating;
}