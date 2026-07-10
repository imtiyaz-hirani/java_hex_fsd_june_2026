package com.springapp.repository;

import com.springapp.dto.StatDto;
import com.springapp.model.Ticket;
import com.springapp.utility.TicketUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<StatDto> getTicketStat() {
        String sql = """
                select c.name as customer_name, count(*) as num_of_tickets
                from customers c JOIN tickets t ON t.customer_id = c.id
                group by c.name
                """;

        return jdbcTemplate.query(sql,(ResultSet rst, int rowNum) -> {
              StatDto dto = new StatDto(
                      rst.getString("customer_name"),rst.getInt("num_of_tickets"));
            return dto;
        });

    }
}
