package com.springapp;

import com.springapp.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // Load AppConfig.java
        // This is Reaching out to Spring's context and asking it to load AppConfig.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Spring looks into its context, and pulls out CustomerService object and gives it to us.
        CustomerService customerService = context.getBean(CustomerService.class);

        System.out.println(customerService.sayHello());

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