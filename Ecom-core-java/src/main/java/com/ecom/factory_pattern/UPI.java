package com.ecom.factory_pattern;

public class UPI implements Payment{

    @Override
    public void process(Account account) {
        System.out.println("Processing UPI Payment....... ");
        System.out.println("Amount " + account.amount() + " processed");
    }
}
