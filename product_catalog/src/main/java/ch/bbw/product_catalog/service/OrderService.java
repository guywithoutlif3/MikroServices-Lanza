package ch.bbw.product_catalog.service;

import ch.bbw.product_catalog.dto.PlaceOrderDto;
import ch.bbw.product_catalog.exceptions.UserNotFoundException;
import ch.bbw.product_catalog.model.Customer;
import ch.bbw.product_catalog.model.OrderPlacement;
import ch.bbw.product_catalog.repository.OrderRepository;
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

    public void placeOrder(PlaceOrderDto orderDto){
        Customer customer = customerService.findCustomer(orderDto.getCustomerUsername());
        if (customer != null){
            OrderPlacement confirmedOrder = new OrderPlacement(Date.valueOf(LocalDate.now()), customer);
            orderDto.getProducts().forEach(product -> confirmedOrder.getProducts().add(product));
            orderRepository.save(confirmedOrder);
        } else {
            throw new UserNotFoundException("User: " + orderDto.getCustomerUsername() + " was not found in the database");
        }
    }
}
