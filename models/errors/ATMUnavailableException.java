package models.errors;

public class ATMUnavailableException extends RuntimeException {
  public ATMUnavailableException() {
    super("caixa-indisponivel");
  }
}
