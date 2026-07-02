package com.hbm;

import com.hbm.config.HbmConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SupportFlowApp {

    public static void main(String[] args) {
        SessionFactory sessionFactory =  HbmConfig.getSessionFactory();
        // Session session = sessionFactory.openSession();
        System.out.println("Works!!");
        HbmConfig.closeFactory();
    }
}
