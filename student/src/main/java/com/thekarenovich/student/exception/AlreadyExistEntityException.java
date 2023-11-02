package com.thekarenovich.student.exception;

public class AlreadyExistEntityException extends RuntimeException {
    public AlreadyExistEntityException(String s) {
        super(s);
    }
}