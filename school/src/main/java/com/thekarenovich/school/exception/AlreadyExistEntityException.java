package com.thekarenovich.school.exception;

public class AlreadyExistEntityException extends RuntimeException {
    public AlreadyExistEntityException(String s) {
        super(s);
    }
}