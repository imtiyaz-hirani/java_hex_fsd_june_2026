package com.springapp.service;

import com.springapp.model.Customer;
import com.springapp.repository.CustomerRepository;
import com.springapp.utility.CustomerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
       return customerRepository.getAllCustomers();
    }

    public Customer getById(int id) {
        // try this, if id is valid, we get Customer - best case
        // if it turns out to be invalid or non-existent, then orElseThrow gets executed
        return customerRepository.getById(id)
                .orElseThrow(()-> new RuntimeException("Id not found.."));
    }

    public void insert(Customer customer) {
        customerRepository.insert(customer);
    }


    /*
    public String sayHello(){
        System.out.println("JDBC created at loc: " +jdbcTemplate);
        return customerUtility.helloUtil() + "Spring"; // Howdy Spring
    }
    */
}
/*
Dependency Injection - DI

1. Use Constructor  -- recommended
2. Use Setter
3. Use Autowiring
* */


//    @Autowired
//   private CustomerUtility customerUtility;  // IOC: Giving control of Object to Spring
//
//    private CustomerUtility customerUtility; // Dependency - CustomerService depends on this class --DI
//
//    @Autowired
//    public void setCustomerUtility(CustomerUtility customerUtility) {
//        this.customerUtility = customerUtility;
//    }