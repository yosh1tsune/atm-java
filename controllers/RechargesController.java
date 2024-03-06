package controllers;

import models.Atm;
import models.Recharge;
import services.RechargeATMService;

import org.json.*;

public class RechargesController {
  private static Atm atm;

  public static void recharge(JSONObject json){
    try {
      atm = atm("java");

      Recharge recharge = new Recharge(json.getBoolean("caixaDisponivel"), json.getJSONObject("notas"));

      RechargeATMService service = new RechargeATMService(recharge, atm);

      service.execute();

      render(atm);
    } catch (Exception e) {
      render(atm, e.getMessage());
    }
  }

  public static Atm atm(String name) {
    Atm atm = Atm.find(name);

    if (atm != null)
      return atm;
    else
      return Atm.create();
  }

  public static void render(Atm atm) {
    System.out.println();
    System.out.println(atm.toJson());
  }
  public static void render(Atm atm, String message) {
    JSONObject json = atm.toJson().getJSONObject("caixa").append("erros", message);

    System.out.println();
    System.out.println(json);
  }
}
