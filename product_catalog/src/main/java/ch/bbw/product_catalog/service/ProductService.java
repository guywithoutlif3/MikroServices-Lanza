package ch.bbw.product_catalog.service;

import ch.bbw.product_catalog.model.Product;
import ch.bbw.product_catalog.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

}
