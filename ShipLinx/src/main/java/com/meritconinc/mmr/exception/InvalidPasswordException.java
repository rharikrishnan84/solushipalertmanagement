package com.meritconinc.mmr.exception;

public class InvalidPasswordException extends Exception {

  public InvalidPasswordException(String string) {
    super(string);
  }

  public InvalidPasswordException() {
    super();
  }
}
