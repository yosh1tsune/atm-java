package controllers;

import models.Atm;
import models.Recharge;
import models.errors.ATMUnderUseException;
import services.RechargeATMService;

import org.json.*;

public class RechargesController extends ApplicationController {
  private static Atm atm;

  public static void recharge(JSONObject json){
    try {
      atm = atm("java");

      Recharge recharge = new Recharge(json.getBoolean("caixaDisponivel"), json.getJSONObject("notas"));

      new RechargeATMService(recharge, atm).execute();

      render(atm);
    } catch (ATMUnderUseException e) {
      render(atm, e.getMessage());
    }
  }

  public static Atm atm(String name) {
    if (Atm.getAtms().isEmpty())
      return Atm.create();
    else
      return Atm.find(name);
  }
}
