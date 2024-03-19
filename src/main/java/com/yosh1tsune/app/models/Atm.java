package com.yosh1tsune.app.models;

import java.util.ArrayList;

import org.json.JSONObject;

public class Atm {
  private static ArrayList<Atm> atms = new ArrayList<>();

  public static final ArrayList<JSONObject> POSSIBLE_NOTES = new ArrayList<JSONObject> () {
    {
      add(new JSONObject("{ name: notasCem, nominal_value: 100 }"));
      add(new JSONObject("{ name: notasCinquenta, nominal_value: 50 }"));
      add(new JSONObject("{ name: notasVinte, nominal_value: 20 }"));
      add(new JSONObject("{ name: notasDez, nominal_value: 10 }"));
    }
  };

  private String name;
  private Boolean status;
  private JSONObject notes;

  public Atm() {
    this.name = "java";
    this.status = false;
    this.notes = new JSONObject("{ notasDez: 0, notasVinte: 0, notasCinquenta: 0, notasCem: 0 }");
  }

  public static ArrayList<Atm> getAtms() {
    return atms;
  }

  public static Atm create() {
    return new Atm();
  }

  public static Atm find(String name) {
    Atm found_atm = null;

    for (Atm atm: atms) {
      if (atm.name.equals(name))
        return found_atm = atm;
    }

    return found_atm;
  }

  public JSONObject toJson() {
    String json = String.format("{ caixa: { caixaDisponivel: %s, notas: %s, erros: [] } }", status, notes);

    return new JSONObject(json);
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Boolean getStatus() {
    return status;
  }

  public JSONObject getNotes() {
    return notes;
  }

  public void removeNotes(JSONObject usedNotes) {
    for(String key : usedNotes.keySet()) {
      notes.put(key, notes.getInt(key) - usedNotes.getInt(key));
    }

    save();
  }

  public void addNotes(JSONObject newNotes) {
    for(String key : newNotes.keySet()) {
      notes.put(key, notes.getInt(key) + newNotes.getInt(key));
    }

    save();
  }

  public void save() {
    atms.add(this);
  }

  public int valueAvailable() {
    return (notes.getInt("notasDez") * 10) + (notes.getInt("notasVinte") * 20) + (notes.getInt("notasCinquenta") * 50) + (notes.getInt("notasCem") * 100);
  }
}
