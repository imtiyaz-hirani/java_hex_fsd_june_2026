package com.springboot.ecom.mapper;

import com.springboot.ecom.dto.request.CustomerDto;
import com.springboot.ecom.dto.response.CustomerRespDto;
import com.springboot.ecom.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static Customer mapDtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.name());
        customer.setCity(customerDto.city());
        return customer;
    }

    public static CustomerRespDto mapEntityToDto(Customer customer){
        CustomerRespDto dto = new CustomerRespDto(

                customer.getName(),
                customer.getCity()
        );
        return dto;
    }
}
