package com.setec.school_management.exceptions.exceptions;

public class MyResourceNotFoundException extends RuntimeException {
    public MyResourceNotFoundException(String msg) {
        super(msg);
    }
}