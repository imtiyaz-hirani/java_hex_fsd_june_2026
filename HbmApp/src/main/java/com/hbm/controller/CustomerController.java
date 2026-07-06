package com.hbm.controller;

import com.hbm.model.Customer;
import com.hbm.model.PlanCustomer;
import com.hbm.service.CustomerService;

public class CustomerController {
    private final CustomerService customerService = new CustomerService();

    public PlanCustomer getCustomerWithPlanUsingJoin(int customerId) {
        return customerService.getCustomerWithPlanUsingJoin(customerId);
    }
}
