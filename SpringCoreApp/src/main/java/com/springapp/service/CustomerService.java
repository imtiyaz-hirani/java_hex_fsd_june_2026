package com.springapp.service;

import com.springapp.utility.CustomerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

//    @Autowired
//   private CustomerUtility customerUtility;  // IOC: Giving control of Object to Spring
//
//    private CustomerUtility customerUtility; // Dependency - CustomerService depends on this class --DI
//
//    @Autowired
//    public void setCustomerUtility(CustomerUtility customerUtility) {
//        this.customerUtility = customerUtility;
//    }
    private final CustomerUtility customerUtility;
    private final JdbcTemplate jdbcTemplate;

    public CustomerService(CustomerUtility customerUtility, JdbcTemplate jdbcTemplate) {
        this.customerUtility = customerUtility;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String sayHello(){
        System.out.println("JDBC created at loc: " +jdbcTemplate);
        return customerUtility.helloUtil() + "Spring"; // Howdy Spring
    }


}
/*
Dependency Injection - DI

1. Use Constructor  -- recommended
2. Use Setter
3. Use Autowiring
* */