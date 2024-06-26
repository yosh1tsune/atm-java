package com.yosh1tsune.app.controllers;

import org.json.*;

import com.yosh1tsune.app.models.Atm;
import com.yosh1tsune.app.models.Recharge;
import com.yosh1tsune.app.models.errors.ATMUnderUseException;
import com.yosh1tsune.app.services.RechargeATMService;

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
      return new Atm();
    else
      return Atm.find(name);
  }
}
