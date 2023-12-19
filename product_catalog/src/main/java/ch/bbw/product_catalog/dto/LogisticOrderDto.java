package ch.bbw.product_catalog.dto;

import ch.bbw.product_catalog.model.OrderPlacement;
import ch.bbw.product_catalog.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LogisticOrderDto {
    private String firstname;
    private String address;
    private String lastname;
    private int postalCode;
    private List<Product> products;
    private Date date;
}
