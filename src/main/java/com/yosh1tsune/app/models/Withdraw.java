package com.yosh1tsune.app.models;

import java.util.ArrayList;
import java.util.Date;

public class Withdraw {
  private static ArrayList<Withdraw> withdrawals = new ArrayList<>();

  private int value;
  private Date time;

  public Withdraw(int value, Date time) {
    this.value = value;
    this.time = time;
  }

  public static ArrayList<Withdraw> getWithdrawals() {
    return withdrawals;
  }

  public void save() {
    withdrawals.add(this);
  }

  public int getValue() {
    return value;
  }

  public Date getTime() {
    return time;
  }

  public String toString() {
    return String.format("%nValor do Saque: %d%nHorário: %2$td/%2$tm/%2$tY %2$TT", value, time);
  }
}
