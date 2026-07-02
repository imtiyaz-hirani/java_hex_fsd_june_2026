package com.ecom.factory_pattern;

public class Controller {

    public static void main(String[] args) {
        // In this design Pattern, Controller does not have to create any object by its own, it
        // simply relies on factory clas to provie instance and call the method.

        System.out.println("Customer 1 Paying.....");
        PaymentFactory.processPayment(PaymentType.UPI, new Account(1002, 233));

        System.out.println("Customer 2 Paying.....");
        PaymentFactory.processPayment(PaymentType.CC, new Account(1002, 233));

        System.out.println("Customer 3 Paying.....");
        PaymentFactory.processPayment(PaymentType.NEFT, new Account(1002, 233));

    }
}
