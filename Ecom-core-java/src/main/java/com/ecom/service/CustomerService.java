package com.ecom.service;

import com.ecom.dto.CustomerDto;
import com.ecom.enums.SortDirection;
import com.ecom.repository.CustomerRepository;
import com.ecom.repository.impl.CustomerRepositoryImpl;
import com.ecom.utility.CustomerSortUtility;

import java.util.List;

public class CustomerService {
    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    public List<CustomerDto> fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(int productId) {
        return customerRepository.fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(productId);
    }

    public List<CustomerDto> sortCustomerListByPurchaseDate(List<CustomerDto> list, SortDirection sortDirection) {
        list.sort(new CustomerSortUtility(sortDirection));
        return list;
    }
}
