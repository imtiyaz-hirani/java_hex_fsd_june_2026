package com.hbm;

import com.hbm.config.HbmConfig;
import com.hbm.controller.TicketController;
import com.hbm.enums.Priority;
import com.hbm.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class SupportFlowApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketController ticketController = new TicketController();
        while(true){
            System.out.println("--------------Ticket Ops------------");
            System.out.println("1. Insert Ticket");
            System.out.println("2. Get Ticket by Id");
            System.out.println("3. Get all Tickets");
            System.out.println("4. Delete Ticket");
            System.out.println("5. Update Ticket");
            System.out.println("0. To Exit");
            System.out.println("------------------------------------");
            int input = sc.nextInt();
            if(input == 0){
                System.out.println("Exiting..");
                break; // breaks the while loop
            }
            switch(input){
                case 1 -> {
                    System.out.println("--------Insert Op----------");
                    try {
                        Ticket ticket = new Ticket();
                        ticket.setSubject("Internet Issue");
                        ticket.setDescription("My Internet is dead, need urgent help");
                        ticket.setPriority(Priority.HIGH);
                        ticket = ticketController.insertTicket(ticket);
                        System.out.println("Ticket Inserted in DB");
                        System.out.println("Final Saved Ticket: " );
                        System.out.println(ticket);
                    }
                    catch(RuntimeException e){
                        System.out.println("Insert op could not go thru " + e.getMessage());
                    }
                }
                case 2-> {
                    System.out.println("---Ticket by ID---");
                    int id=1;
                    Ticket ticket = ticketController.getById(id);
                    if(ticket == null)
                        System.out.println("Ticket not found... ");
                    else{
                        System.out.println("Ticket fetched for id " + id );
                        System.out.println(ticket);
                    }

                }
                case 3 ->{
                    List<Ticket> list = ticketController.getAll();
                    list.forEach(System.out :: println);
                }
                case 4 ->{
                    System.out.println("Enter id to delete");
                    int id = sc.nextInt();
                    try {
                        ticketController.deleteById(id);
                        System.out.println("Ticket deleted");
                    }
                    catch(RuntimeException e){
                        System.out.println("Could not delete ticket: " + e.getMessage());
                    }
                }
            }
        }
        HbmConfig.closeFactory();
        sc.close();
    }
}
