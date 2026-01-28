package com.krushkov.virtualwallet.exceptions;

public class AuthorizationFailureException extends RuntimeException {
  public AuthorizationFailureException(String message) {
    super(message);
  }
}
