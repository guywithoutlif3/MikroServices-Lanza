package ch.bbw.warenkorb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemsRESTController {

    @Autowired
    private ItemsRepository itemsRepository;
    @GetMapping("items")
    public List<Item> getJokes() {
        return itemsRepository.findAll();
    }

    @PostMapping("/items")
    public Item createItem(@RequestBody Item newItem) {
        return itemsRepository.save(newItem);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable String id) {
        itemsRepository.deleteById(id);
    }
}
