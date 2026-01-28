package com.krushkov.virtualwallet.exceptions;

public class AuthenticationFailureException extends RuntimeException {
  public AuthenticationFailureException(String message) {
    super(message);
  }
}
