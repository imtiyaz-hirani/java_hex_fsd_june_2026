package com.hbm.dao;

import com.hbm.model.Ticket;

public interface TicketDao {
    Ticket saveTicket(Ticket ticket);

    Ticket getById(int id);
}
