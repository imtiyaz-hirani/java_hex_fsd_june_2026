package com.hbm.config;

import com.hbm.model.Customer;
import com.hbm.model.Employee;
import com.hbm.model.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HbmConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                config.setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost:3306/supportDb");
                config.setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root");
                config.setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "deepcoder");
                config.setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");

                config.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
                config.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");

                // Register the model classes
                config.addAnnotatedClass(Ticket.class);
                config.addAnnotatedClass(Customer.class);
                config.addAnnotatedClass(Employee.class);

                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties())
                        .build();

                sessionFactory = config.buildSessionFactory(registry);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static void closeFactory(){
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
