package com.springapp.utility;

import com.springapp.enums.JobTitle;
import com.springapp.enums.Priority;
import com.springapp.enums.Status;
import com.springapp.model.Customer;
import com.springapp.model.Employee;
import com.springapp.model.Ticket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class TicketUtility implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rst, int rowNum) throws SQLException {
        Customer customer = new Customer();
        Employee employee = new Employee();
        Ticket ticket = new Ticket();

        customer.setId(rst.getInt("id"));
        customer.setName(rst.getString("name"));
        customer.setEmail(rst.getString("email"));
        customer.setCity(rst.getString("city"));

        employee.setId(rst.getInt("id"));
        employee.setName(rst.getString("name"));
        employee.setJobTitle(JobTitle.valueOf(rst.getString("jobTitle")));

        ticket.setId(rst.getInt("id"));
        ticket.setSubject(rst.getString("subject"));
        ticket.setStatus(Status.valueOf(rst.getString("status")));
        ticket.setPriority(Priority.valueOf(rst.getString("priority")));
        ticket.setCreatedAt(rst.getTimestamp("createdAt").toInstant());

        ticket.setEmployee(employee);
        ticket.setCustomer(customer);

        return ticket;
    }
}
