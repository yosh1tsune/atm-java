package models;

import java.util.ArrayList;

import org.json.JSONObject;

public class Recharge {
  private static ArrayList<Recharge> recharges = new ArrayList<>();

  private Boolean status;
  private JSONObject notes;

  public Recharge(Boolean status, JSONObject notes) {
    this.status = status;
    this.notes = notes;
  }

  public Boolean getStatus() {
    return status;
  }

  public JSONObject getNotes() {
    return notes;
  }

  public void save(Recharge recharge) {
    recharges.add(recharge);
  }
}
