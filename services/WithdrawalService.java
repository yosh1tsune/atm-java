package services;

import models.Withdraw;

public class WithdrawalService {
  public Withdraw withdrawal;

  public WithdrawalService(Withdraw withdrawal) {
    this.withdrawal = withdrawal;
  }

  public void execute() {
    System.out.println(withdrawal);
  }
}
