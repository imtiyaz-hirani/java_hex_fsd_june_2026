package com.hbm.dao.impl;

import com.hbm.config.HbmConfig;
import com.hbm.dao.TicketDao;
import com.hbm.enums.Priority;
import com.hbm.enums.Status;
import com.hbm.model.Ticket;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    @Override
    public Ticket update(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(ticket);
            // this ticket has an id attached which is already present in db, so this will merge with old ticket
            // and override old values with new ones (subject, description, priority)
            transaction.commit();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getByCustomer(int customerId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Ticket> query =  session.createQuery("from Ticket t where t.customer.id=:id" , Ticket.class);
            query.setParameter("id" , customerId);
            List<Ticket> list = query.list();
            transaction.commit();
            return list;
        }

    }

    @Override
    public List<Ticket> getByCustomerV2(int customerId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Ticket> query =  session.createNativeQuery("select * from tickets t where t.customer_id=:id"
                    , Ticket.class);
            query.setParameter("id", customerId);
            List<Ticket> list = query.list();
            transaction.commit();
            return list;
        }
    }

    @Override
    public List<Ticket> filterByPriorityAndStatus(Priority priority, Status status) {

        try(Session session = sessionFactory.openSession()){

            // Create a CriteriaBuilder: because this will help us define conditions(predicates) and create query
            CriteriaBuilder cb =  session.getCriteriaBuilder();

            // Create the query
            CriteriaQuery<Ticket> cq =  cb.createQuery(Ticket.class);

            // create a from statement for the query
            Root<Ticket> root = cq.from(Ticket.class); // this is DB record

            // to define the where clause -- predicates
            List<Predicate> predicates = new ArrayList<>();

            if(priority != null)
                predicates.add(cb.equal(root.get("priority") , priority));

            if(status != null)
                predicates.add(cb.equal(root.get("status") , status));

            // where needs a var args(...) which means it needs an array not a List
            cq.where(predicates.toArray(Predicate[]::new));

            Query<Ticket> query =  session.createQuery(cq);

            return query.list();
        }

    }
}
/*
Native Query:
select * from tickets
HQL: this queries model classes
from  Ticket

Native Query:
select * from tickets t where t.customer_id=?
HQL :
from Ticket t where t.customer.id=?
JPQL:
select t from Ticket t where t.customer.id=?
* */