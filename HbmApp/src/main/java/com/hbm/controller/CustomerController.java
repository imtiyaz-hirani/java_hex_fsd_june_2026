package com.hbm.controller;

import com.hbm.dto.CustomerPlanDto;
import com.hbm.model.Customer;
import com.hbm.model.PlanCustomer;
import com.hbm.service.CustomerService;

public class CustomerController {
    private final CustomerService customerService = new CustomerService();

    public CustomerPlanDto getCustomerWithPlanUsingJoin(int customerId) {
        return customerService.getCustomerWithPlanUsingJoin(customerId);
    }
}
