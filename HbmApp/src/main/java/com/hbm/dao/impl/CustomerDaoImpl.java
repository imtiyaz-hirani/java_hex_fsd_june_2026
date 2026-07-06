package com.hbm.dao.impl;

import com.hbm.config.HbmConfig;
import com.hbm.dao.CustomerDao;
import com.hbm.model.Customer;
import com.hbm.model.PlanCustomer;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer getById(int customerId) {
        try(Session session = HbmConfig.getSessionFactory().openSession()){
           Customer customer = session.get(Customer.class,customerId);
           return customer;
        }
    }

    @Override
    public PlanCustomer getCustomerWithPlan(int customerId) {
        String jpql = """
                select pc
                from  PlanCustomer pc
                where pc.customer.id = ?1
                """;
        String hql = """
                from  PlanCustomer pc
                where pc.customer.id = ?
                """;
        try(Session session = HbmConfig.getSessionFactory().openSession()){
            Query<PlanCustomer> query =  session.createQuery(jpql , PlanCustomer.class );
            query.setParameter(1, customerId);
            if(query.getResultCount() == 1)
                    return query.getSingleResult();
            throw new RuntimeException("Customer with id: " + customerId + ",does not have any associated plans");        }

    }
}
/*
  customers   plan_customer  plan
  Customer    PlanCustomer   Plan

  JPQL:
  select pc
  from  PlanCustomer pc
  where pc.customer.id = ?

* */