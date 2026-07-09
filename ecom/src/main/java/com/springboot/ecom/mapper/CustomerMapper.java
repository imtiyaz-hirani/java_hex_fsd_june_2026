package com.springboot.ecom.mapper;

import com.springboot.ecom.dto.request.CustomerDto;
import com.springboot.ecom.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer convertDtoToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setCity(dto.city());
        return customer;
    }
}
