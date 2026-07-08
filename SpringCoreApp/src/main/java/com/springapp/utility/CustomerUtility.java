package com.springapp.utility;

import org.springframework.stereotype.Component;

@Component //<-- I am adding this class to Spring's Context
public class CustomerUtility {

    public String helloUtil(){
        return "Howdy ";
    }
}
