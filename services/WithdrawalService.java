package services;

import models.Withdraw;

import org.json.JSONObject;

import models.Atm;
import services.WithdrawalService;

public class WithdrawalService {
  private Withdraw withdraw;
  private Atm atm;

  public WithdrawalService(Withdraw withdraw, Atm atm) {
    this.withdraw = withdraw;
    this.atm = atm;
  }

  public void execute() throws Exception {
    if (withdraw.getValue() > atm.valueAvailable() || (withdraw.getValue() % 10) != 0 )
      throw new Exception("valor-indisponivel");

    selectNotes();
  }

  public void selectNotes() {
    int dummyValue = withdraw.getValue();
    int notesQuantity;
    JSONObject usedNotes = new JSONObject();

    for (JSONObject possibleNotes : Atm.POSSIBLE_NOTES) {
      while(dummyValue >= possibleNotes.getInt("nominal_value")) {
        notesQuantity = 0;
        if (dummyValue >= possibleNotes.getInt("nominal_value") && atm.getNotes().getInt("notasCem") > 0) {
          System.out.println(possibleNotes);
          notesQuantity = dummyValue / possibleNotes.getInt("nominal_value");
          usedNotes.put(possibleNotes.getString("name"), notesQuantity);
          dummyValue -= notesQuantity * possibleNotes.getInt("nominal_value");
        }
      }
    }

    atm.removeNotes(usedNotes);
  }
}
