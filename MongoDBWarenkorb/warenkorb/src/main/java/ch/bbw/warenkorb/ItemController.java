package ch.bbw.warenkorb;

import ch.bbw.warenkorb.dto.PlaceOrderDto;
import ch.bbw.warenkorb.dto.ReceiveOrderDto;
import ch.bbw.warenkorb.repositories.ItemsRepository;
import ch.bbw.warenkorb.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("items")
public class ItemController {

    private ItemsRepository itemsRepository;
    private final ItemService itemService;
    @GetMapping
    public List<Item> getCart() {
        return itemsRepository.findAll();
    }

    @PostMapping
    public Item createItem(@RequestBody Item newItem) {
        return itemsRepository.save(newItem);
    }

    @PostMapping("/placeorder")
    public ResponseEntity<List<Item>> placeOrderWithCart(@RequestBody ReceiveOrderDto receiveOrderDto){
        Optional<PlaceOrderDto> placedOrder = itemService.placeOrderByCart(receiveOrderDto.getCustomerName()).blockOptional();
        if(placedOrder.isEmpty()){
            throw new RuntimeException("No items found in shopping cart for user: " + receiveOrderDto.getCustomerName());
        }
        return ResponseEntity.ok(placedOrder.get().getProducts());
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemsRepository.deleteById(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleUserNotFound(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
