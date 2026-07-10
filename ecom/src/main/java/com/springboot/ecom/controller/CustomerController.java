package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.CustomerDto;
import com.springboot.ecom.dto.response.CustomerRespDto;
import com.springboot.ecom.model.Customer;
import com.springboot.ecom.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public Customer add(@Valid @RequestBody CustomerDto customerDto){
        return customerService.add(customerDto);
    }

    @GetMapping("/get-all")
    public List<CustomerRespDto> getAll(@RequestParam Integer page,
                                        @RequestParam Integer size){
        return customerService.getAll(page,size);
    }

    @GetMapping("/get-one/{id}")
    public CustomerRespDto getById(@PathVariable long id){
        return customerService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        customerService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable long id,
                       @Valid @RequestBody CustomerDto customerDto){
        customerService.update(id, customerDto);
    }
}
