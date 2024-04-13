package com.yosh1tsune.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test the state of an ATM after the recharge proccess")
public class WithdrawTest {
  Date date = new Date();
  Withdraw withdraw = new Withdraw(0, date);

  @BeforeEach
  void clearDatabase(){
    Withdraw.getWithdrawals().clear();
  }

  @DisplayName("Successfully instantiate an withdraw object with informed parameters")
  @Test
  void instantiation(){
    Withdraw newWithdraw = new Withdraw(100, date);

    assertEquals(newWithdraw.getValue(), 100);
    assertEquals(newWithdraw.getTime(), date);
  }

  @Test
  void getValue(){
    assertEquals(withdraw.getValue(), 0);
  }

  @Test
  void getTime(){
    assertEquals(withdraw.getTime(), date);
  }

  @Test
  void save(){
    withdraw.save();

    assertEquals(Withdraw.getWithdrawals().size(), 1);
    assertEquals(Withdraw.getWithdrawals().contains(withdraw), true);
  }

  @DisplayName("Test overrided method toString()")
  @Test
  void toStringTest(){
    assertEquals(withdraw.toString(), String.format("%nValor do Saque: %d%nHorário: %2$td/%2$tm/%2$tY %2$TT", 0, date));
  }

  @Test
  void getWithdrawals(){
    withdraw.save();
    ArrayList<Withdraw> array = new ArrayList<Withdraw>() { { add(withdraw); } };

    assertEquals(Withdraw.getWithdrawals(), array);
  }
}
