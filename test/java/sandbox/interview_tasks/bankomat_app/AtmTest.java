package sandbox.interview_tasks.bankomat_app;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static sandbox.interview_tasks.bankomat_app.Banknote.*;

class AtmTest {

    @Test
    void takeOf() {
        Atm atm = new Atm();

        atm.initializeBanknotes((Map.of(
                ONE_HUNDRED, 1200,
                TWO_HUNDRED, 400,
                FIVE_HUNDRED, 500,
                ONE_THOUSAND, 10000,
                TWO_THOUSAND, 200,
                FIVE_THOUSAND, 7000)));

        var answer = atm.takeOf(10200, new DebitCard("1", 500000));
        assertEquals(Map.of(FIVE_THOUSAND, 2,
                TWO_HUNDRED, 1), answer);
    }

    @Test
    void takeOfNotEnoughBanknotes() {
        Atm atm = new Atm();

        atm.initializeBanknotes((Map.of(ONE_THOUSAND, 1,
                TWO_THOUSAND, 1)));


        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () ->  atm.takeOf(2500, new DebitCard("1", 500000)),
                "Not enough banknotes=500 Please enter sum multiple banknotes=1000"
        );

        assertTrue(thrown.getMessage().contains("Not enough banknotes"));

    }
}