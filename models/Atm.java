package models;
import java.util.ArrayList;
import java.util.Arrays;

public class Atm {
  private static ArrayList<String> atms = new ArrayList<>();


  public static ArrayList<String> getAtm(){
    return atms;
  }

  public static void addAtm(String atm){
    atms.add(atm);
  }
}
