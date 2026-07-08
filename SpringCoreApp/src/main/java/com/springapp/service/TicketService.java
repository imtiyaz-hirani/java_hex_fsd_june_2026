package com.springapp.service;

import com.springapp.model.Ticket;
import com.springapp.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllWithCustomerAndEmployee() {
        return ticketRepository.getAllWithCustomerAndEmployee();
    }
}
