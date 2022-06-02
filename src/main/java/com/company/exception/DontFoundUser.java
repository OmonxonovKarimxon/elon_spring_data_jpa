package com.company.exception;

public class DontFoundUser extends RuntimeException {
    public DontFoundUser(String message) {
        super(message);
    }
}
