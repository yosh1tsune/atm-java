package com.yosh1tsune.app.models.errors;

public class DuplicatedWithdrawalException extends RuntimeException {
  public DuplicatedWithdrawalException() {
    super("saque-duplicado");
  }
}
