package ru.moskalev.sandbox.interview_tasks.atm_app;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.moskalev.sandbox.interview_tasks.atm_app.Banknote.*;

class AtmTest {

    @Test
    void takeOfLargeAlgorithm() {
        Atm atm = new Atm();

        atm.initializeBanknotes((Map.of(
                ONE_HUNDRED, 1200,
                TWO_HUNDRED, 400,
                FIVE_HUNDRED, 500,
                ONE_THOUSAND, 10000,
                TWO_THOUSAND, 200,
                FIVE_THOUSAND, 7000)));

        var answer = atm.takeOfLargeAlgorithm(10200, new DebitCard("1", 500000));
        assertEquals(Map.of(FIVE_THOUSAND, 2,
                TWO_HUNDRED, 1), answer);
    }

    @Test
    void takeOfLargeAlgorithmNotEnoughBanknotes() {
        Atm atm = new Atm();

        atm.initializeBanknotes((Map.of(ONE_THOUSAND, 1,
                TWO_THOUSAND, 1)));


        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () ->  atm.takeOfLargeAlgorithm(2500, new DebitCard("1", 500000)),
                "Not enough banknotes=500"
        );

        assertTrue(thrown.getMessage().contains("Not enough banknotes"));

    }

    @Test
    void brokeLargeAlgorithm() {
        Atm atm = new Atm();

        atm.initializeBanknotes((Map.of(
                TWO_HUNDRED, 3,
                FIVE_HUNDRED, 5)));


        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () ->  atm.takeOfLargeAlgorithm(600, new DebitCard("1", 500000)),
                "Not enough banknotes for amount=100 banknotes=200"
        );

        assertTrue(thrown.getMessage().contains("Not enough banknotes"));

    }


    @Test
    void takeOfDynamicAlg() {
        Atm atm = new Atm();
        atm.initializeBanknotes((Map.of(ONE_THOUSAND, 3,
                FIVE_HUNDRED, 2,
                TWO_HUNDRED,3)));
        atm.takeOfDynamicAlgorithm(3200, new DebitCard("1", 500000));
    }

    @Test
    void fill() {
        Atm atm = new Atm();
        atm.initializeBanknotes((Map.of(ONE_THOUSAND, 3,
                FIVE_HUNDRED, 2,
                TWO_HUNDRED,3)));
        atm.fill(Arrays.asList(FIVE_HUNDRED,
                ONE_HUNDRED,TEST));
    }
}