package com.springapp.service;

import com.springapp.dto.StatDto;
import com.springapp.model.Ticket;
import com.springapp.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketService {
    private final TicketRepository ticketRepository; // DI

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllWithCustomerAndEmployee() {
        return ticketRepository.getAllWithCustomerAndEmployee();
    }

    public List<StatDto> getTicketStat() {
        return ticketRepository.getTicketStat();
        /*
        Iterate over the List<StatDto> that you are getting from repository layer and put value in the map
        * */
    }
}
