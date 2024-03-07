package controllers;

import services.WithdrawalService;
import models.Atm;
import models.Withdraw;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class WithdrawalsController {
  private static Atm atm;

  public static void withdrawal(JSONObject json) throws ParseException, Exception {
    try {
      atm = atm("java");

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

      Withdraw withdrawal = new Withdraw(json.getInt("valor"), sdf.parse(json.getString("horario")));

      WithdrawalService service = new WithdrawalService(withdrawal, atm);

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
