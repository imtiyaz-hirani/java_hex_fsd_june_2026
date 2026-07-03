package com.hbm.dao.impl;

import com.hbm.config.HbmConfig;
import com.hbm.dao.TicketDao;
import com.hbm.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private final SessionFactory sessionFactory;

    public TicketDaoImpl() {
        sessionFactory =  HbmConfig.getSessionFactory();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        Transaction transaction = null;
         try(Session session = sessionFactory.openSession()){
             // If you want hibernate to perform DB ops, you must work with transaction
             /*
             SEQUENCE:
             1. begin the transaction
             2. perform db op
             3. commit the transaction
              */
             transaction = session.beginTransaction();
             session.persist(ticket);
             transaction.commit();

             return ticket;
         }
         catch(Exception e){
             if(transaction != null)
                 transaction.rollback();
             throw new RuntimeException(e.getMessage());
         }

    }

    @Override
    public Ticket getById(int id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket =  session.find(Ticket.class, id);
            transaction.commit();

            return ticket;
        }
        catch(Exception e){
            if(transaction != null)
                transaction.rollback();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Ticket> getAll() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
             // Actual DB call
            Query<Ticket> query = session.createQuery("from Ticket", Ticket.class);
            List<Ticket> list = query.list();
            transaction.commit();
            return list;
        }
        catch(Exception e){
            if(transaction != null)
                transaction.rollback();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void deleteById(int id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Fetch ticket object by id to make sure id is valid
            Ticket ticket =  session.find(Ticket.class, id);
            if(ticket != null) {
                session.remove(ticket); // remove after confirmation
                transaction.commit();
                return;
            }

             transaction.rollback();
             throw new RuntimeException("Invalid ID given ");
        }

    }
}
/*
Native Query:
select * from tickets
HQL: this queries model classes
from  Ticket
* */