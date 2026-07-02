package com.ecom.factory_pattern;

public class Neft implements Payment{

    @Override
    public void process(Account account) {
        System.out.println("Processing NEFT Payment....... ");
        System.out.println("Amount " + account.amount() + "will be processed in 2 hrs");
    }
}
