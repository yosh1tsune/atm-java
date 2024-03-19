package com.yosh1tsune.app.models.errors;

public class ValueUnavailableException extends RuntimeException {
  public ValueUnavailableException() {
    super("valor-indiponivel");
  }
}
