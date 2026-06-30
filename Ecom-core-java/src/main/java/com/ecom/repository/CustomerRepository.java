package com.ecom.repository;

import com.ecom.dto.CustomerDto;

import java.util.List;

public interface CustomerRepository {
    List<CustomerDto> fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(int productId);
}
