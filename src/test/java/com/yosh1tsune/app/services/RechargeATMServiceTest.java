package com.yosh1tsune.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yosh1tsune.app.models.Atm;
import com.yosh1tsune.app.models.Recharge;
import com.yosh1tsune.app.models.errors.ATMUnderUseException;

@DisplayName("Test the state of an ATM after the recharge proccess")
public class RechargeATMServiceTest {
  @AfterEach
  void clearDatabase(){
    Recharge.setRecharges(new ArrayList<Recharge>());
  }

  @Test
  @DisplayName("Recharge atm successfully")
  void success(){
    JSONObject json = new JSONObject("{ notasDez: 0, notasVinte: 0, notasCinquenta: 0, notasCem: 10 }");
    Atm atm = new Atm();
    Recharge recharge = new Recharge(true, json);

    new RechargeATMService(recharge, atm).execute();

    assertEquals(atm.getStatus(), true);
    assertEquals(atm.getNotes().toString(), json.toString());
    assertEquals(Recharge.getRecharges().size(), 1);
    assertEquals(Recharge.getRecharges().contains(recharge), true);
  }

  @Test
  @DisplayName("Throw atm under use exception")
  void fail(){
    JSONObject json = new JSONObject("{ notasDez: 0, notasVinte: 0, notasCinquenta: 0, notasCem: 10 }");
    Atm atm = new Atm(true, json);
    Recharge recharge = new Recharge(true, json);

    assertThrows(ATMUnderUseException.class, () -> new RechargeATMService(recharge, atm).execute());
    assertEquals(atm.getStatus(), true);
    assertEquals(atm.getNotes(), json);
    assertEquals(Recharge.getRecharges().size(), 0);
    assertEquals(Recharge.getRecharges().contains(recharge), false);
  }
}
