package com.yosh1tsune.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yosh1tsune.app.models.Atm;
import com.yosh1tsune.app.models.Recharge;

public class RechargeATMServiceTest {
  static JSONObject json = new JSONObject("{ notasDez: 0, notasVinte: 0, notasCinquenta: 0, notasCem: 0 }");
  static Atm atm = new Atm();
  static Recharge recharge = new Recharge(true, json);

  @BeforeAll
  static void run(){
    new RechargeATMService(recharge, atm).execute();
  }

  @Test
  @DisplayName("execute")
  void execute(){
    assertEquals(atm.getStatus(), true);
    assertEquals(atm.getNotes().toString(), json.toString());
  }
}
