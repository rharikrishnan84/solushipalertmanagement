package com.meritconinc.mmr.exception;

public class CustomerNameAlreadyTakenException extends Exception {

  public CustomerNameAlreadyTakenException(String string) {
    super(string);
  }

  public CustomerNameAlreadyTakenException() {
    super();
  }
}
