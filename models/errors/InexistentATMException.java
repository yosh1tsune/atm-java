package models.errors;

public class InexistentATMException extends RuntimeException {
  public InexistentATMException() {
    super("caixa-inexistente");
  }
}
