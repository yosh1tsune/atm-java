package services;

import models.Withdraw;
import models.Atm;
import models.errors.DuplicatedWithdrawalException;
import models.errors.ValueUnavailableException;
import services.WithdrawalService;

import org.json.JSONObject;

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
    JSONObject usedNotes = new JSONObject();

    for (JSONObject possibleNotes : Atm.POSSIBLE_NOTES) {
      if (atm.getNotes().getInt(possibleNotes.getString("name")) == 0)
        continue;

      while(dummyValue >= possibleNotes.getInt("nominal_value")) {
        notesQuantity = 0;
        if (dummyValue >= possibleNotes.getInt("nominal_value")) {
          notesQuantity = dummyValue / possibleNotes.getInt("nominal_value");
          usedNotes.put(possibleNotes.getString("name"), notesQuantity);
          dummyValue -= notesQuantity * possibleNotes.getInt("nominal_value");
        }
      }
    }

    if (dummyValue > 0 )
      throw new ValueUnavailableException();

    return usedNotes;
  }
}
