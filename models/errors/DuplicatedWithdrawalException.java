package models.errors;

public class DuplicatedWithdrawalException extends RuntimeException {
  public DuplicatedWithdrawalException() {
    super("saque-duplicado");
  }
}
