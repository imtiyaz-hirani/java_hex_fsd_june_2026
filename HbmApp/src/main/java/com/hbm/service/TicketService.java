package com.hbm.service;

import com.hbm.dao.TicketDao;
import com.hbm.dao.impl.TicketDaoImpl;
import com.hbm.enums.Status;
import com.hbm.model.Ticket;

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
}
