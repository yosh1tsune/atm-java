package controllers;

import services.WithdrawalService;
import models.Withdraw;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class WithdrawalsController {
  public static void withdrawal(JSONObject json) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    Withdraw withdrawal = new Withdraw(json.getInt("valor"), sdf.parse(json.getString("horario")));

    WithdrawalService service = new WithdrawalService(withdrawal);

    service.execute();
  }
}
