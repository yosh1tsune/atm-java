package models;

import java.util.ArrayList;

public class Recharge {
  public static ArrayList<String> recharges = new ArrayList<>();

  public void save(String recharge) {
    recharges.add(recharge);
  }
}
