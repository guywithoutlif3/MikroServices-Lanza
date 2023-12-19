package ch.bbw.logisticprocessor.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Document(collection = "orders")
public class ConfirmedOrder {
    @Id
    private String id;
    private String firstname;
    private String address;
    private String lastname;
    private int postalCode;
    private List<Product> products;
    private Date date;
}
