package models;
import java.util.ArrayList;

import org.json.JSONObject;

public class Atm {
  private static ArrayList<Atm> atms = new ArrayList<>();

  public static final String[] POSSIBLE_NOTES = {
      "notasDez",
      "notasVinte",
      "notasCinquenta",
      "notasCem"
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
    for(String note : POSSIBLE_NOTES) {
      notes.put(note, notes.getInt(note) - usedNotes.getInt(note));
    }

    save();
  }

  public void addNotes(JSONObject newNotes) {
    for(String note : POSSIBLE_NOTES) {
      notes.put(note, notes.getInt(note) + newNotes.getInt(note));
    }

    save();
  }

  public void save() {
    atms.add(this);
  }
}
