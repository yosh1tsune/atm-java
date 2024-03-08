package models.errors;

public class ValueUnavailableException extends RuntimeException {
  public ValueUnavailableException() {
    super("valor-indiponivel");
  }
}
