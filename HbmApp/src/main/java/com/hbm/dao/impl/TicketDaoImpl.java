package com.hbm.dao.impl;

import com.hbm.config.HbmConfig;
import com.hbm.dao.TicketDao;
import com.hbm.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
}
