package com.hbm.dao;

import com.hbm.enums.Priority;
import com.hbm.enums.Status;
import com.hbm.model.Ticket;

import java.util.List;

public interface TicketDao {
    Ticket saveTicket(Ticket ticket);

    Ticket getById(int id);

    List<Ticket> getAll();

    void deleteById(int id);

    Ticket update(Ticket ticket);

    List<Ticket> getByCustomer(int customerId);

    List<Ticket> getByCustomerV2(int customerId);

    List<Ticket> filterByPriorityAndStatus(Priority priority, Status status);
}
