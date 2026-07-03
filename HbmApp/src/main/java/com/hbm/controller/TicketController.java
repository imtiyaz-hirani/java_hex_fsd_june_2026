package com.hbm.controller;

import com.hbm.enums.Priority;
import com.hbm.enums.Status;
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

    public Ticket update(Ticket ticket, String subject, String description, String priority) {
        return ticketService.update(ticket, subject, description, priority);
    }

    public List<Ticket> getByCustomer(int customerId) {
        return ticketService.getByCustomer(customerId);
    }

    public List<Ticket> filterByPriorityAndStatus(Priority priority, Status status) {
        return ticketService.filterByPriorityAndStatus(priority, status);
    }
}
