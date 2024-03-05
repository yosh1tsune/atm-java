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
  private JSONObject notes;

  public static ArrayList<Atm> getAtms() {
    return atms;
  }

  public static Atm find(String name) {
    Atm found_atm = null;

    for (Atm atm: atms) {
      if (atm.name.equals(name))
        return found_atm = atm;
    }

    return found_atm;
  }

  public void removeNotes(JSONObject usedNotes) {
    for(String note : POSSIBLE_NOTES) {
      notes.put(note, notes.getInt(note) - usedNotes.getInt(note));
    }

    save();
  }

  public void save() {
    atms.add(this);
  }
}
