package com.yosh1tsune.app.models.errors;

public class ATMUnderUseException extends RuntimeException {
  public ATMUnderUseException() {
    super("caixa-em-uso");
  }
}
