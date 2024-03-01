package controllers;

import services.RechargeATMService;

public class RechargesController {
  public static void recharge(String recharge){
    RechargeATMService service = new RechargeATMService(recharge);

    service.execute();
  }
}
