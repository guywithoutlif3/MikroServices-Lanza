package ch.bbw.logisticprocessor.model;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Product {
    private Long productId;
    private String imgLink;
    private String name;
    private String size;
    private double price;
    private int rating;
}
