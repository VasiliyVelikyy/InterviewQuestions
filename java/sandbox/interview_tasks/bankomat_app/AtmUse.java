package sandbox.interview_tasks.bankomat_app;

import java.util.Map;
import java.util.TreeMap;

import static sandbox.interview_tasks.bankomat_app.Banknote.*;

public class AtmUse {
    public static void main(String[] args) {
        Atm atm = new Atm();

        atm.initializeBanknotes(new TreeMap<>(Map.of(
                ONE_HUNDRED, 1200,
                TWO_HUNDRED, 400,
                FIVE_HUNDRED, 500,
                ONE_THOUSAND, 10000,
                TWO_THOUSAND, 200,
                FIVE_THOUSAND, 7000)));

    }
}
