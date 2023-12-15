package ch.bbw.product_catalog.service;

import ch.bbw.product_catalog.exceptions.DuplicateUsernameException;
import ch.bbw.product_catalog.model.Customer;
import ch.bbw.product_catalog.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void addNewCustomer(Customer customer){
        try {
            customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            // Check if the exception is caused by a duplicate username
            if (isDuplicateUsernameException(e)) {
                throw new DuplicateUsernameException("Username already exists.");
            } else {
                // Re-throw the original exception if it's not related to a duplicate username
                throw e;
            }
        }
    }

    private boolean isDuplicateUsernameException(DataIntegrityViolationException e) {
        return e.getMessage().contains("Duplicate entry");
    }

    public Customer findCustomer(String username){
        return customerRepository.findCustomerByUsername(username);
    }
}
