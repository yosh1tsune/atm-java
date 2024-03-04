package models;
import java.util.ArrayList;

public class Atm {
  private static ArrayList<String> atms = new ArrayList<>();


  public static ArrayList<String> getAtms() {
    return atms;
  }

  public static void save(String atm) {
    atms.add(atm);
  }

  public static String find(String name) {
    String found_atm = new String();

    for (String atm: atms) {
      if (atm == name)
        found_atm = atm;
    }

    return found_atm;
  }
}
