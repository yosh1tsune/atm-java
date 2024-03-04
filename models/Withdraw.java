package models;

import java.util.ArrayList;
import java.util.Date;

public class Withdraw {
  private static ArrayList<String> withdrawals = new ArrayList<>();

  private int value;
  private Date time;

  public static void save(String withdraw) {
    withdrawals.add(withdraw);
  }

  public Withdraw(int value, Date time) {
    this.value = value;
    this.time = time;
  }

  public String toString() {
    return String.format("%nValor do Saque: %d%nHorário: %2$td/%2$tm/%2$tY %2$TT", value, time);
  }
}
