package ch.bbw.product_catalog.controller;

import ch.bbw.product_catalog.exceptions.DuplicateUsernameException;
import ch.bbw.product_catalog.model.Customer;
import ch.bbw.product_catalog.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception or perform additional handling if needed
        log.error(e.getMessage());
        return new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<String> handleDuplicateUser(DuplicateUsernameException e) {
        // Log the exception or perform additional handling if needed
        log.error(e.getMessage());
        return new ResponseEntity<>("Username already exists. Try another one", HttpStatus.BAD_REQUEST);
    }
}
