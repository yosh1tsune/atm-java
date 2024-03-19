package com.yosh1tsune.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.yosh1tsune.app.models.Atm;
import com.yosh1tsune.app.models.Withdraw;
import com.yosh1tsune.app.models.errors.ATMUnavailableException;
import com.yosh1tsune.app.models.errors.DuplicatedWithdrawalException;
import com.yosh1tsune.app.models.errors.InexistentATMException;
import com.yosh1tsune.app.models.errors.ValueUnavailableException;
import com.yosh1tsune.app.services.WithdrawalService;

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
    } catch (ParseException | ValueUnavailableException | DuplicatedWithdrawalException | ATMUnavailableException e) {
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
