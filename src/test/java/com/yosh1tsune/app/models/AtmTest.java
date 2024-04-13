package com.yosh1tsune.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Atm initialization and methods")
public class AtmTest {
  JSONObject notes = new JSONObject("{ notasDez: 10, notasVinte: 20, notasCinquenta: 50, notasCem: 100 }");
  Atm atm = new Atm(true, notes);

  @BeforeEach
  void clearDatabase(){
    Atm.getAtms().clear();
  }

  @Test
  @DisplayName("Class method find(), return matching object inside classe variable atms")
  void find(){
    atm.save();

    assertEquals(Atm.find("java"), atm);
  }

  @Test
  @DisplayName("Instantiaton without parameters informed")
  void blankConstructor(){
    Atm defaultAtm = new Atm();

    assertEquals(defaultAtm.getStatus(), false);
    assertEquals(defaultAtm.getNotes().toString(), new JSONObject("{ notasDez: 0, notasVinte: 0, notasCinquenta: 0, notasCem: 0 }").toString());
  }

  @Test
  @DisplayName("Instantiaton with parameters informed")
  void constructorWithParameters(){
    Atm parametrizedAtm = new Atm(true, notes);

    assertEquals(parametrizedAtm.getStatus(), true);
    assertEquals(parametrizedAtm.getNotes(), notes);
  }

  @Test
  @DisplayName("Instance method setStatus(), update value for instance variable status")
  void setStatus(){
    atm.setStatus(false);

    assertEquals(atm.getStatus(), false);
  }

  @Test
  @DisplayName("Instance method toJson()")
  void toJson(){
    assertEquals(atm.toJson().toString(), new JSONObject(String.format("{ caixa: { caixaDisponivel: %s, notas: %s, erros: [] } }", true, notes)).toString());
  }

  @Test
  @DisplayName("Instance method valueAvailable(), multiply notes by their value and sum total")
  void valueAvailable(){
    assertEquals(atm.valueAvailable(), 13_000);
  }

  @Test
  @DisplayName("Instance method addNotes(), update Atm.notes with sum of actual values with received parameter")
  void addNotes(){
    atm.addNotes(new JSONObject("{ notasDez: 5, notasVinte: 5, notasCinquenta: 5, notasCem: 5 }"));

    assertEquals(atm.getNotes().toString(), new JSONObject("{ notasDez: 15, notasVinte: 25, notasCinquenta: 55, notasCem: 105 }").toString());
  }

  @Test
  @DisplayName("Instance method removeNotes(), update Atm.notes with subtraction of actual values with received parameter")
  void removeNotes(){
    atm.removeNotes(new JSONObject("{ notasDez: 5, notasVinte: 5, notasCinquenta: 5, notasCem: 5 }"));

    assertEquals(atm.getNotes().toString(), new JSONObject("{ notasDez: 5, notasVinte: 15, notasCinquenta: 45, notasCem: 95 }").toString());
  }

  @Test
  @DisplayName("Instance method save(), store the object into the class variable atms")
  void save(){
    atm.save();

    assertEquals(Atm.getAtms().size(), 1);
    assertEquals(Atm.getAtms().contains(atm), true);
  }
}
