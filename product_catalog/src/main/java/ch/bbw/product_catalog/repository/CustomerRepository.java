package ch.bbw.product_catalog.repository;

import ch.bbw.product_catalog.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByUsername(String userName);
}
