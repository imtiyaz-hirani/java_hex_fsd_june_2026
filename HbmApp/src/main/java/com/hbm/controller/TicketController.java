package com.hbm.controller;

import com.hbm.model.Ticket;
import com.hbm.service.TicketService;

public class TicketController {
    TicketService ticketService = new TicketService();

    public Ticket insertTicket(Ticket ticket) {
        return ticketService.insertTicket(ticket);
    }
}
