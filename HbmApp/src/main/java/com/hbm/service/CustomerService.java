package com.hbm.service;

import com.hbm.dao.CustomerDao;
import com.hbm.dao.impl.CustomerDaoImpl;
import com.hbm.dto.CustomerPlanDto;
import com.hbm.mapper.CustomerPlanMapper;
import com.hbm.model.Customer;
import com.hbm.model.PlanCustomer;

public class CustomerService {

    private final CustomerDao customerDao = new CustomerDaoImpl();
    private final CustomerPlanMapper customerPlanMapper = new CustomerPlanMapper();

    public CustomerPlanDto getCustomerWithPlanUsingJoin(int customerId) {
        // ensure that customer id exists
       Customer customer =  customerDao.getById(customerId);
       if(customer == null)
           throw new RuntimeException("Customer with id: " + customerId + ",could not be found");

      PlanCustomer planCustomer =  customerDao.getCustomerWithPlan(customerId);

      // Map this entity to a Dto and return the dto
       CustomerPlanDto dto =  customerPlanMapper.mapEntityToDto(planCustomer);
       return dto;
    }
}
