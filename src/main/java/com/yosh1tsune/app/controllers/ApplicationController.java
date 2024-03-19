package com.yosh1tsune.app.controllers;

import org.json.JSONObject;

import com.yosh1tsune.app.models.Atm;

public class ApplicationController {
  public static void render(Atm atm) {
    System.out.println();
    System.out.println(atm.toJson());
    System.out.println();
  }

  public static void render(String message) {
    System.out.println();
    System.out.println(String.format("{ caixa: {}, erros: [%s] }", message));
    System.out.println();
  }

  public static void render(Atm atm, String message) {
    JSONObject json = atm.toJson().getJSONObject("caixa").append("erros", message);

    System.out.println();
    System.out.println(json);
    System.out.println();
  }
}
