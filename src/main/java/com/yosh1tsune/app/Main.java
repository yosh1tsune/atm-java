package com.yosh1tsune.app;
import java.util.Scanner;
import org.json.JSONObject;

import com.yosh1tsune.app.controllers.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input;
    try {
      do {
        input = scanner.nextLine();

        JSONObject json = new JSONObject(input);

        if (json.has("caixa")) {
          RechargesController.recharge(json.getJSONObject("caixa"));
        }
        else if (json.has("saque")) {
          WithdrawalsController.withdrawal(json.getJSONObject("saque"));
        }
      } while (input != "exit");
    } catch (Exception e) {
    } finally {
      scanner.close();
    }
  }
}
