package com.yosh1tsune.app.services;

import org.json.JSONObject;

import com.yosh1tsune.app.models.Atm;
import com.yosh1tsune.app.models.Withdraw;
import com.yosh1tsune.app.models.errors.DuplicatedWithdrawalException;
import com.yosh1tsune.app.models.errors.ValueUnavailableException;
import com.yosh1tsune.app.services.WithdrawalService;

import java.time.temporal.ChronoUnit;

public class WithdrawalService {
  private Withdraw withdraw;
  private Atm atm;

  public WithdrawalService(Withdraw withdraw, Atm atm) {
    this.withdraw = withdraw;
    this.atm = atm;
  }

  public void execute() throws ValueUnavailableException, DuplicatedWithdrawalException {
    validate();

    atm.removeNotes(selectNotes());

    withdraw.save();
  }

  private void validate() {
    if (isDuplicated())
      throw new DuplicatedWithdrawalException();

    if (withdraw.getValue() > atm.valueAvailable() || (withdraw.getValue() % 10) != 0 )
      throw new ValueUnavailableException();
  }

  private Boolean isDuplicated() {
    for (Withdraw oldWithdraw : Withdraw.getWithdrawals()) {
      if (withdraw.getValue() == oldWithdraw.getValue() && minutesDiff(oldWithdraw) <= 10)
        return true;
    }

    return false;
  }

  private long minutesDiff(Withdraw oldWithdraw) {
    return Math.abs(ChronoUnit.MINUTES.between(withdraw.getTime().toInstant(), oldWithdraw.getTime().toInstant()));
  }

  private JSONObject selectNotes() {
    int dummyValue = withdraw.getValue();
    int notesQuantity;
    int notesAvailable;
    JSONObject usedNotes = new JSONObject();

    for (JSONObject possibleNotes : Atm.POSSIBLE_NOTES) {
      notesAvailable = atm.getNotes().getInt(possibleNotes.getString("name"));
      if (notesAvailable == 0)
        continue;

      while(
        dummyValue >= possibleNotes.getInt("nominal_value") &&
        (dummyValue <= notesAvailable * possibleNotes.getInt("nominal_value"))
      ){
        notesQuantity = 0;
        if (dummyValue >= possibleNotes.getInt("nominal_value")) {
          notesQuantity = dummyValue / possibleNotes.getInt("nominal_value");
          usedNotes.put(possibleNotes.getString("name"), notesQuantity);
          dummyValue -= notesQuantity * possibleNotes.getInt("nominal_value");
        }
      }
    }

    if (dummyValue > 0)
      throw new ValueUnavailableException();

    return usedNotes;
  }
}
