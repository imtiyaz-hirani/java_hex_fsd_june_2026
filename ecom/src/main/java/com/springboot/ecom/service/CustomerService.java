package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.CustomerDto;
import com.springboot.ecom.mapper.CustomerMapper;
import com.springboot.ecom.model.Customer;
import com.springboot.ecom.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public Customer add(CustomerDto dto) {
        // Covert Dto to Entity using Mapper class
        Customer customer = customerMapper.convertDtoToEntity(dto);

        // Give to repository to save this in DB
       return customerRepository.save(customer);
    }
}
