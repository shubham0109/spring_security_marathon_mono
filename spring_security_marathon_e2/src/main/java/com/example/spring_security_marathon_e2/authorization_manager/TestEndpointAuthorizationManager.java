package com.example.spring_security_marathon_e2.authorization_manager;

import org.springframework.stereotype.Component;

@Component
public class TestEndpointAuthorizationManager {

    public boolean authorize(Type t, String smth){
        // authorization logic goes here
        //return true;  // authorized
        //return false;  // not authorized
        return Type.B == t;
    }

    enum Type {
        A, B, C, D;
    }




}
