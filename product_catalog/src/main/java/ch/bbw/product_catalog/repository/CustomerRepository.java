package ch.bbw.product_catalog.repository;

import ch.bbw.product_catalog.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByUsername(String userName);
}
