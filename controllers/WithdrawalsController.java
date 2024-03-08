package controllers;

import services.WithdrawalService;
import models.Atm;
import models.Withdraw;
import models.errors.ValueUnavailableException;
import models.errors.ATMUnavailableException;
import models.errors.InexistentATMException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class WithdrawalsController {
  private static Atm atm;

  public static void withdrawal(JSONObject json)
    throws ParseException, ValueUnavailableException, InexistentATMException, ATMUnavailableException {
    try {
      atm = atm("java");

      if (atm == null)
        throw new InexistentATMException();

      if (atm.getStatus() == false)
        throw new ATMUnavailableException();

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

      Withdraw withdrawal = new Withdraw(json.getInt("valor"), sdf.parse(json.getString("horario")));

      WithdrawalService service = new WithdrawalService(withdrawal, atm);

      service.execute();

      render(atm);
    } catch (ParseException | ValueUnavailableException | ATMUnavailableException e) {
      render(atm, e.getMessage());
    } catch (InexistentATMException e) {
      render(e.getMessage());
    }
  }

  public static Atm atm(String name) {
    return Atm.find(name);
  }

  public static void render(Atm atm) {
    System.out.println();
    System.out.println(atm.toJson());
    System.out.println();
  }

  public static void render(String message) {
    System.out.println();
    System.out.println(String.format("{ caixa: {}, erros: [%s] }", message));
    System.out.println();
  }

  public static void render(Atm atm, String message) {
    JSONObject json = atm.toJson().getJSONObject("caixa").append("erros", message);

    System.out.println();
    System.out.println(json);
    System.out.println();
  }
}
