package ch.bbw.product_catalog.service;

import ch.bbw.product_catalog.dto.PlaceOrderDto;
import ch.bbw.product_catalog.exceptions.ProductNotFoundException;
import ch.bbw.product_catalog.exceptions.UserNotFoundException;
import ch.bbw.product_catalog.model.Customer;
import ch.bbw.product_catalog.model.OrderPlacement;
import ch.bbw.product_catalog.model.Product;
import ch.bbw.product_catalog.repository.OrderRepository;
import ch.bbw.product_catalog.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductRepository productRepository;

    @Transactional
    public void placeOrder(PlaceOrderDto orderDto){
        Customer customer = customerService.findCustomer(orderDto.getCustomerUsername());
        if (customer != null){
            OrderPlacement confirmedOrder = new OrderPlacement(Date.valueOf(LocalDate.now()), customer);
            orderDto.getProducts().forEach(product -> {
                Product confirmedProduct = productRepository.findById(product.getProductId())
                        .orElseThrow(() -> new ProductNotFoundException("Product: " + product.getName() + " with the id: "
                                + product.getProductId() + " not found"));
                confirmedOrder.getProducts().add(confirmedProduct);
            });

            orderRepository.save(confirmedOrder);
        } else {
            throw new UserNotFoundException("User: " + orderDto.getCustomerUsername() + " was not found in the database");
        }
    }
}
