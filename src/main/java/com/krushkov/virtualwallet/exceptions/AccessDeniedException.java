package com.krushkov.virtualwallet.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String action, String type) {
        super(String.format("You don't have permission to %s this %s.", action, type));
    }
}
