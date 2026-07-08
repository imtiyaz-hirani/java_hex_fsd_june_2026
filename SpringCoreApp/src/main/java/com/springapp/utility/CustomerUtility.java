package com.springapp.utility;

import com.springapp.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component //<-- I am adding this class to Spring's Context
public class CustomerUtility implements RowMapper<Customer> {

    public String helloUtil(){
        return "Howdy ";
    }

    @Override
    public Customer mapRow(ResultSet rst, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rst.getInt("id"));
        customer.setName(rst.getString("name"));
        customer.setEmail(rst.getString("email"));
        customer.setCity(rst.getString("city"));
        return customer;
    }
}

/*
(ResultSet rst, int rowNum) -> {
        Customer customer = new Customer();
        customer.setId(rst.getInt("id"));
        customer.setName(rst.getString("name"));
        customer.setEmail(rst.getString("email"));
        customer.setCity(rst.getString("city"));
        return customer;
    }

* */
