package controllers;

import services.RechargeATMService;

import org.json.*;

public class RechargesController {
  public static void recharge(JSONObject recharge){
    RechargeATMService service = new RechargeATMService(recharge);

    service.execute();
  }
}
