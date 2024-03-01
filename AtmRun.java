import java.util.Scanner;
import models.*;

import java.util.ArrayList;
import java.util.Arrays;

public class AtmRun {
  public static void main(String[] args){
    Atm.addAtm("Bariloche");

    ArrayList<String> atms = Atm.getAtm();
    for (String atm: atms) {
      System.out.println(atm);
    }
  }
}
