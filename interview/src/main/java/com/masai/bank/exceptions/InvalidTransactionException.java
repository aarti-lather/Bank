package com.masai.bank.exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException() {
    }

    public InvalidTransactionException(String message) {
        super(message);
    }
}
