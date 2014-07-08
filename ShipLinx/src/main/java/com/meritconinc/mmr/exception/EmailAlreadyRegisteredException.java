package com.meritconinc.mmr.exception;

public class EmailAlreadyRegisteredException extends Exception {

  public EmailAlreadyRegisteredException(String string) {
    super(string);
  }

  public EmailAlreadyRegisteredException() {
    super();
  }
}
