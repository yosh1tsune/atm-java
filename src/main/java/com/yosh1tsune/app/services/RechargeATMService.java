package com.yosh1tsune.app.services;

import com.yosh1tsune.app.models.Atm;
import com.yosh1tsune.app.models.Recharge;
import com.yosh1tsune.app.models.errors.ATMUnderUseException;

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
