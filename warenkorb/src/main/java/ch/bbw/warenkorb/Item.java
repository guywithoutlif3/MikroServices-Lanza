package ch.bbw.warenkorb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // Lombok getter und Setter
@Document(collection = "items")
public class Item {
    // TODO Correct the attributes according to relational database, add customer name for request
    @Id
    private long productId;
    private String name;
    private double price;
    private String size;
    private int rating;
    private String imgLink;
    private String customerUsername;
}