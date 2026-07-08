package com.springapp.repository;

import com.springapp.model.Customer;
import com.springapp.utility.CustomerUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository //<-- This annotation adds CustomerRepository to spring context
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerUtility customerUtility;

    public CustomerRepository(JdbcTemplate jdbcTemplate,
                              CustomerUtility customerUtility) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerUtility = customerUtility;
    }

    public List<Customer> getAllCustomers() {
        String sql = "select * from customers";
        return jdbcTemplate.query(sql,customerUtility);

//        return jdbcTemplate.query(sql, (ResultSet rst, int rowNum) -> {
//            Customer customer = new Customer();
//            customer.setId(rst.getInt("id"));
//            customer.setName(rst.getString("name"));
//            customer.setEmail(rst.getString("email"));
//            customer.setCity(rst.getString("city"));
//            return customer;
//        });
    }

    // Optional is a wrapper class, which gives you the object(Customer) if the id is valid
    // but if Optional does not have the object ,it will be empty
    public Optional<Customer> getById(int id) {
        String sql = "select * from customers where id=?";
        return jdbcTemplate.query(sql, customerUtility, id)
                .stream()
                .findFirst();
    }

    public void insert(Customer customer) {
        String sql = "insert into customers(name,city,email) values (?,?,?)";
        Object[] values = new Object[]{customer.getName(), customer.getCity(), customer.getEmail()};
        jdbcTemplate.update(sql, values);
    }

    public void delete(int id) {
        String sql = "delete from customers where id = ?";
        Object[] values = new Object[]{id};
        jdbcTemplate.update(sql, values);
    }
}
