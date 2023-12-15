package ch.bbw.product_catalog.dto;

import ch.bbw.product_catalog.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PlaceOrderDto {
    private String customerUsername;
    private List<Product> products;
}
