package ch.bbw.warenkorb.dto;

import ch.bbw.warenkorb.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDto {
    private String customerUsername;
    private List<Item> products;
}
