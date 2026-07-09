package com.springboot.ecom.repository;

import com.springboot.ecom.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    // now this repository all has all JpaRepository methods
    /*
    - save
    - findAll
    - findById
    - deleteById
    * */
}

/*
Spring Data Jpa has a super
JpaRepository interface.
it has following methods:
- save
- findAll
- findById
- deleteById

* */