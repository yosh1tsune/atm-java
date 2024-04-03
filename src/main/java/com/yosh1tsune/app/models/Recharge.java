package com.yosh1tsune.app.models;

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

  public static ArrayList<Recharge> getRecharges(){
    return recharges;
  }

  public static void setRecharges(ArrayList<Recharge> newRecharges){
    recharges = newRecharges;
  }

  public Boolean getStatus() {
    return status;
  }

  public JSONObject getNotes() {
    return notes;
  }

  public void save() {
    recharges.add(this);
  }
}
