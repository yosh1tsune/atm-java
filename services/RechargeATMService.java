package services;

import models.Atm;
import models.Recharge;

public class RechargeATMService {
  private Recharge recharge;
  private Atm atm;

  public RechargeATMService(Recharge recharge, Atm atm) {
    this.recharge = recharge;
    this.atm = atm;
  }

  public void execute() throws Exception {
    if (atm.getStatus() == true)
      throw new Exception("caixa-em-uso");

    updateAtm(atm);
  }

  public void updateAtm(Atm atm) {
    atm.setStatus(recharge.getStatus());
    atm.addNotes(recharge.getNotes());
  }
}
