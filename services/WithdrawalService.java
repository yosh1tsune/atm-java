package services;

public class WithdrawalService {
  public String withdrawal;

  public WithdrawalService(String withdrawal) {
    this.withdrawal = withdrawal;
  }

  public void execute() {
    System.out.println("WithdrawalService");
  }
}
