package services;

import models.Atm;


public class RechargeATMService {

  public RechargeATMService(JSONObject recharge) {
    this.recharge = recharge;
  }

  public void execute() {
    System.out.println("RechargeATMService");
  }

  public String atm(String name) {
    return Atm.find(name);
  }
}
