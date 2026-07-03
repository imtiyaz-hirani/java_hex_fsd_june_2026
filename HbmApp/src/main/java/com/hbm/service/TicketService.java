package com.hbm.service;

import com.hbm.dao.TicketDao;
import com.hbm.dao.impl.TicketDaoImpl;
import com.hbm.enums.Priority;
import com.hbm.enums.Status;
import com.hbm.model.Ticket;

import java.util.List;

public class TicketService {
    TicketDao ticketDao = new TicketDaoImpl(); // Polymorphic Object

    public Ticket insertTicket(Ticket ticket) {
        /*
        this ticket object has following fields coming from end user,
        1. subject
        2. description
        3. priority

        I will add status as OPEN to it and let hibernate worry about other fields
        * */

        ticket.setStatus(Status.OPEN);
       return ticketDao.saveTicket(ticket);
    }

    public Ticket getById(int id) {
        return ticketDao.getById(id);
    }

    public List<Ticket> getAll() {
        return ticketDao.getAll();
    }

    public void deleteById(int id) {
        ticketDao.deleteById(id);
    }

    public Ticket update(Ticket ticket, String subject, String description, String priority) {
        // ticket obj that needs to be updated already has an id attached to it.
        // dont change this id. update the other fields that are allowed to be updated
        ticket.setSubject(subject);
        ticket.setDescription(description);
        ticket.setPriority(Priority.valueOf(priority));
        // Pass this updated ticket to Dao for insertion
        return ticketDao.update(ticket);
    }
}
