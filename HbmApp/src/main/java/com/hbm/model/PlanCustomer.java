package com.hbm.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "plan_customer")
public class PlanCustomer { //pc

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Customer  1:M     CustomerPlan    M:1      Plan
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; // creates FK customer_id   -- pc.customer

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan; //pc.plan

    private int customerPerValue;

    @CreationTimestamp
    private Instant startDate;

    private Instant dateEndDate;

    private double amountPaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public int getCustomerPerValue() {
        return customerPerValue;
    }

    public void setCustomerPerValue(int customerPerValue) {
        this.customerPerValue = customerPerValue;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getDateEndDate() {
        return dateEndDate;
    }

    public void setDateEndDate(Instant dateEndDate) {
        this.dateEndDate = dateEndDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "PlanCustomer{" +
                "id=" + id +
                ", customer=" + customer +
                ", plan=" + plan +
                ", customerPerValue=" + customerPerValue +
                ", startDate=" + startDate +
                ", dateEndDate=" + dateEndDate +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
