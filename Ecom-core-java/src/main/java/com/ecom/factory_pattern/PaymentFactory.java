package com.ecom.factory_pattern;

public class PaymentFactory {

    public static void processPayment(PaymentType type , Account account){
        switch (type){
            case PaymentType.UPI -> new UPI().process(account);
            case PaymentType.CC -> new CC().process(account);
            case PaymentType.NEFT -> new Neft().process(account);
        }
    }
}
