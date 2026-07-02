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
//        System.out.println("--------Insert Op----------");
//        try {
//            Ticket ticket = new Ticket();
//            ticket.setSubject("Internet Issue");
//            ticket.setDescription("My Internet is dead, need urgent help");
//            ticket.setPriority(Priority.HIGH);
//            ticket = ticketController.insertTicket(ticket);
//            System.out.println("Ticket Inserted in DB");
//            System.out.println("Final Saved Ticket: " );
//            System.out.println(ticket);
//        }
//        catch(RuntimeException e){
//            System.out.println("Insert op could not go thru " + e.getMessage());
//        }

        System.out.println("---Ticket by ID---");
        int id=1;
        Ticket ticket = ticketController.getById(id);
        if(ticket == null)
            System.out.println("Ticket not found... ");
        else{
            System.out.println("Ticket fetched for id " + id );
            System.out.println(ticket);
        }


        HbmConfig.closeFactory();
    }
}
