package com.example.spring_security_marathon_e2.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message){
        System.out.println(message);
    }
}
