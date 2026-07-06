package com.hbm.dao;

import com.hbm.model.Customer;
import com.hbm.model.PlanCustomer;

public interface CustomerDao {
    Customer getById(int customerId);

    PlanCustomer getCustomerWithPlan(int customerId);
}
