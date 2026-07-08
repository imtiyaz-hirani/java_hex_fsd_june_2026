package com.springapp.repository;

import com.springapp.model.Ticket;
import com.springapp.utility.TicketUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TicketUtility ticketUtility;

    public TicketRepository(JdbcTemplate jdbcTemplate, TicketUtility ticketUtility) {
        this.jdbcTemplate = jdbcTemplate;
        this.ticketUtility = ticketUtility;
    }

    public List<Ticket> getAllWithCustomerAndEmployee() {
        String sql= """
                 select *
                 from customers c
                 JOIN tickets t ON t.customer_id = c.id
                 JOIN employees e ON e.id = t.employee_id
                """;
        return jdbcTemplate.query(sql,ticketUtility );
    }
}
