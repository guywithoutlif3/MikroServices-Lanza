package ch.bbw.product_catalog.service;

import ch.bbw.product_catalog.model.Product;
import ch.bbw.product_catalog.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {
    private final ProductRepository productRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getProducts(){
        // TODO: Filter out the products already ordered by a join or native query
        List<Product> products = productRepository.findAll();
        products = products.stream()
                .filter(product -> !isIdInOrderPlacement(product.getProductId()))
                .collect(Collectors.toList());
        return products;
    }

    @Transactional
    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    private boolean isIdInOrderPlacement(Long idToCheck) {
        String nativeQuery = "SELECT COUNT(*) FROM webshop.order_product WHERE product_id = :id";
        Query query = entityManager.createNativeQuery(nativeQuery);
        query.setParameter("id", idToCheck);

        int count = ((Number) query.getSingleResult()).intValue();
        return count > 0;
    }
}
