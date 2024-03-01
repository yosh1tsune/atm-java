package services;

public class RechargeATMService {
  private String recharge;

  public RechargeATMService(String recharge) {
    this.recharge = recharge;
  }

  public void execute() {
    System.out.println("RechargeATMService");
  }
}
