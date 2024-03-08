package controllers;

import models.Atm;
import models.Withdraw;
import models.errors.ValueUnavailableException;
import models.errors.ATMUnavailableException;
import models.errors.InexistentATMException;
import services.WithdrawalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class WithdrawalsController extends ApplicationController {
  private static Atm atm;

  public static void withdrawal(JSONObject json)
    throws ValueUnavailableException, InexistentATMException, ATMUnavailableException {
    try {
      atm = atm("java");

      validate(atm);

      Withdraw withdrawal = new Withdraw(json.getInt("valor"), parsedDate(json));

      new WithdrawalService(withdrawal, atm).execute();

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

  private static void validate(Atm atm) {
    if (atm == null)
      throw new InexistentATMException();

    if (atm.getStatus() == false)
      throw new ATMUnavailableException();
  }

  private static Date parsedDate(JSONObject json) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    return sdf.parse(json.getString("horario"));
  }
}
