package com.hbm.controller;

import com.hbm.model.Ticket;
import com.hbm.service.TicketService;

import java.util.List;

public class TicketController {
    TicketService ticketService = new TicketService();

    public Ticket insertTicket(Ticket ticket) {
        return ticketService.insertTicket(ticket);
    }

    public Ticket getById(int id) {
        return ticketService.getById(id);
    }

    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    public void deleteById(int id) {
        ticketService.deleteById(id);
    }
}
