package services;

import models.Atm;
import models.Recharge;
import models.errors.ATMUnderUseException;

public class RechargeATMService {
  private Recharge recharge;
  private Atm atm;

  public RechargeATMService(Recharge recharge, Atm atm) {
    this.recharge = recharge;
    this.atm = atm;
  }

  public void execute() throws ATMUnderUseException {
    if (atm.getStatus() == true)
      throw new ATMUnderUseException();

    updateAtm(atm);
  }

  public void updateAtm(Atm atm) {
    atm.setStatus(recharge.getStatus());
    atm.addNotes(recharge.getNotes());
  }
}
