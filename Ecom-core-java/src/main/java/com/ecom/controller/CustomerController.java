package com.ecom.controller;

import com.ecom.dto.CustomerDto;
import com.ecom.enums.SortDirection;
import com.ecom.service.CustomerService;

import java.util.List;

public class CustomerController {
    CustomerService customerService = new CustomerService();

    public List<CustomerDto> fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(int productId) {
        return customerService.fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(productId);
    }

    public List<CustomerDto> sortCustomerListByPurchaseDate(SortDirection sortDirection, int productId) {
        List<CustomerDto> list = customerService.fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(productId);
        return customerService.sortCustomerListByPurchaseDate(list,sortDirection);
    }
}
