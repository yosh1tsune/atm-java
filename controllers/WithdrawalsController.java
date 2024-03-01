package controllers;
import services.*;

public class WithdrawalsController {
  public static void withdrawal(String withdrawal) {
    WithdrawalService service = new WithdrawalService(withdrawal);

    service.execute();
  }
}
