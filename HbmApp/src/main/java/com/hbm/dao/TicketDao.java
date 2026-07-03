package com.hbm.dao;

import com.hbm.model.Ticket;

import java.util.List;

public interface TicketDao {
    Ticket saveTicket(Ticket ticket);

    Ticket getById(int id);

    List<Ticket> getAll();

    void deleteById(int id);

    Ticket update(Ticket ticket);
}
