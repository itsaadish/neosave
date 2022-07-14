package com.example.neosave.exceptions;

public class DuplicateEmailFound extends Throwable {
    public DuplicateEmailFound(String message) {
        super(message);
    }
}
