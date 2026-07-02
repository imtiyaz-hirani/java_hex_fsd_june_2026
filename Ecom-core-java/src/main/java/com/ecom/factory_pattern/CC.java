package com.ecom.factory_pattern;

public class CC implements  Payment{
    @Override
    public void process(Account account) {
        System.out.println("Processing CC Payment....... ");
        System.out.println("Amount " + account.amount() + " is processed on credit");
    }
}
