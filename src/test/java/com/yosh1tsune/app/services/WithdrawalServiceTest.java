package com.yosh1tsune.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yosh1tsune.app.models.Atm;
import com.yosh1tsune.app.models.Withdraw;
import com.yosh1tsune.app.models.errors.DuplicatedWithdrawalException;
import com.yosh1tsune.app.models.errors.ValueUnavailableException;

@DisplayName("Test the withdraw process and persistence of the processed withdraw object.")
public class WithdrawalServiceTest {
  @BeforeEach
  void clearDatabase(){
    Withdraw.getWithdrawals().clear();
  }

  @Test
  @DisplayName("Throw exception if withdraw value is not available")
  void valueUnavailable(){
    JSONObject json = new JSONObject("{ notasDez: 1, notasVinte: 1, notasCinquenta: 1, notasCem: 1 }");
    Atm atm = new Atm(true, json);
    Withdraw withdrawal = new Withdraw(200, new Date());

    assertThrows(ValueUnavailableException.class, () -> new WithdrawalService(withdrawal, atm).execute());
    assertEquals(atm.getNotes(), json);
  }

  @Test
  @DisplayName("Throw exception if notes can't compose requested value")
  void notesUnavailable(){
    JSONObject json = new JSONObject("{ notasDez: 2, notasVinte: 0, notasCinquenta: 100, notasCem: 100 }");
    Atm atm = new Atm(true, json);
    Withdraw withdrawal = new Withdraw(30, new Date());

    assertThrows(ValueUnavailableException.class, () -> new WithdrawalService(withdrawal, atm).execute());
    assertEquals(atm.getNotes(), json);
  }

  @Test
  @DisplayName("Throw exception if a same value withdraw was made within 10 minutes")
  void duplicatedWithdrawal(){
    JSONObject json = new JSONObject("{ notasDez: 0, notasVinte: 0, notasCinquenta: 0, notasCem: 2 }");
    Atm atm = new Atm(true, json);
    Withdraw withdrawal = new Withdraw(200, new Date());
    withdrawal.save();

    assertThrows(DuplicatedWithdrawalException.class, () -> new WithdrawalService(withdrawal, atm).execute());
    assertEquals(atm.getNotes(), json);
  }

  @Test
  @DisplayName("Successfull withdraw")
  void success(){
    JSONObject json = new JSONObject("{ notasDez: 0, notasVinte: 0, notasCinquenta: 0, notasCem: 2 }");
    Atm atm = new Atm(true, json);
    Withdraw withdrawal = new Withdraw(200, new Date());

    new WithdrawalService(withdrawal, atm).execute();

    assertEquals(atm.getNotes().getInt("notasCem"), 0);
    assertEquals(Withdraw.getWithdrawals().size(), 1);
    assertEquals(Withdraw.getWithdrawals().contains(withdrawal), true);
  }
}
