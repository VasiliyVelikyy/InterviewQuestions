package ru.moskalev.sandbox.interview_tasks.yandex_atm_app;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtmYandexTest {
    Sdk sdk;

    @Test
    void takeOf() {
        sdk = new StubSdk();
        AtmYandex atm = new AtmYandex(sdk);
        atm.initBankomat(Arrays.asList(50, 100, 500, 1000));

        var answer = atm.takeOf(4200);

        assertEquals(4, answer.get(1000));
    }

    @Test
    void takeOf2() {
        sdk = new StubSdk();
        AtmYandex atm = new AtmYandex(sdk);
        atm.initBankomat(List.of(100));

        var answer = atm.takeOf(1000);

        assertEquals(10, answer.get(100));
    }

    @Test
    void takeOf3() {
        sdk = new StubSdk();
        AtmYandex atm = new AtmYandex(new Sdk() {
            @Override
            public int countBanknotes(int banknote) {
                if(banknote == 100) return 100;

                else return 0;
            }

            @Override
            public void moveBanknoteToDispenser(int banknote, int count) {

            }

            @Override
            public void openDispenser() {

            }
        });


        atm.initBankomat(List.of(200, 1000));
        //потому что банкнот только 2, 1000 и 200 рублей
        assertThrows(IllegalArgumentException.class,()-> atm.takeOf(1100));

    }
}