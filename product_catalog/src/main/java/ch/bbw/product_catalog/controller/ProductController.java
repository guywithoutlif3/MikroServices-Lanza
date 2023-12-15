package ch.bbw.product_catalog.controller;

import ch.bbw.product_catalog.model.Product;
import ch.bbw.product_catalog.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product successfully deleted");
    }
}
