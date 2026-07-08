package com.springapp;

import com.springapp.model.Customer;
import com.springapp.model.Ticket;
import com.springapp.service.CustomerService;
import com.springapp.service.TicketService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Load AppConfig.java
        // This is Reaching out to Spring's context and asking it to load AppConfig.
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        // Spring looks into its context, and pulls out CustomerService object and gives it to us.
        /*
        CustomerService customerService = context.getBean(CustomerService.class);
        List<Customer> list =  customerService.getAllCustomers();
        list.forEach(System.out :: println);
        System.out.println("-------Fetch by Customer id-----------");
        try {
            Customer customer = customerService.getById(1);
            System.out.println(customer);
            customer = customerService.getById(10);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
        }

        System.out.println("----------insert Customer----------");
        Customer customer = new Customer("Harry Potter", "harry@gmail.com", "london");
        customerService.insert(customer);
        System.out.println("Customer added to DB..");
        */
        TicketService ticketService = context.getBean(TicketService.class);
        List<Ticket> list  = ticketService.getAllWithCustomerAndEmployee();
        list.forEach(System.out :: println);
        context.close();
    }
}
/*
If u want all these classes to get registered, you have to tell spring to scan them right at the beginning of the app
Spring Context
--------------
CustomerController
CustomerService
CustomerUtility

JdbcTemplate
DataSource
* */