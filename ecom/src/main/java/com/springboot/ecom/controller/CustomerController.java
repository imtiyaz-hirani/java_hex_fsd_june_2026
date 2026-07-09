package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.CustomerDto;
import com.springboot.ecom.model.Customer;
import com.springboot.ecom.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    /**
     Body: {
     "name" : "harry potter",
     "city" : "london"
     }
     */
    @PostMapping("/add") //api/customer/add
    public Customer add(@Valid @RequestBody CustomerDto dto){
        return customerService.add(dto);
    }

    @GetMapping("/get-all")
    public void getAll(){

    }

    @GetMapping("/get-one")
    public void getById(){

    }

    @DeleteMapping("/delete")
    public void delete(){

    }

    @PutMapping("/update")
    public void update(){

    }
}
