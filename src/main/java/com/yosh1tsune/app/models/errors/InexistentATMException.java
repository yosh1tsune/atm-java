package com.yosh1tsune.app.models.errors;

public class InexistentATMException extends RuntimeException {
  public InexistentATMException() {
    super("caixa-inexistente");
  }
}
