package com.hbm;

import com.hbm.config.HbmConfig;
import com.hbm.controller.TicketController;
import com.hbm.enums.Priority;
import com.hbm.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SupportFlowApp {

    public static void main(String[] args) {
        TicketController ticketController = new TicketController();
        System.out.println("--------Insert Op----------");
        Ticket ticket = new Ticket();
        ticket.setSubject("Internet Issue");
        ticket.setDescription("My Internet is dead, need urgent help");
        ticket.setPriority(Priority.HIGH);

        try {
            ticket = ticketController.insertTicket(ticket);
            System.out.println("Ticket Inserted in DB");
            System.out.println("Final Saved Ticket: " );
            System.out.println(ticket);
        }
        catch(RuntimeException e){
            System.out.println("Insert op could not go thru " + e.getMessage());
        }

        HbmConfig.closeFactory();
    }
}
