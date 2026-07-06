package com.hbm.service;

import com.hbm.dao.CustomerDao;
import com.hbm.dao.impl.CustomerDaoImpl;
import com.hbm.model.Customer;
import com.hbm.model.PlanCustomer;

public class CustomerService {

    private final CustomerDao customerDao = new CustomerDaoImpl();

    public PlanCustomer getCustomerWithPlanUsingJoin(int customerId) {
        // ensure that customer id exists
       Customer customer =  customerDao.getById(customerId);
       if(customer == null)
           throw new RuntimeException("Customer with id: " + customerId + ",could not be found");

      return customerDao.getCustomerWithPlan(customerId);
    }
}
