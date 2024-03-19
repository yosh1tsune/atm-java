package com.yosh1tsune.app.models.errors;

public class ATMUnavailableException extends RuntimeException {
  public ATMUnavailableException() {
    super("caixa-indisponivel");
  }
}
