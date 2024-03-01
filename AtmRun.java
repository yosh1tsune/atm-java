import models.*;
import controllers.*;

import java.util.Scanner;

public class AtmRun {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();

    if (input.equals("caixa")) {
      RechargesController.recharge(input);
    }
    else if (input.equals("saque")) {
      WithdrawalsController.withdrawal(input);
    }
  }
}
