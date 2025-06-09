package ru.moskalev.sandbox.my_examples.multithreading.sync;

import java.util.ArrayList;
import java.util.List;

class Example1 {
    public static void main(String[] args) {

        //запуск не произойдет одновременно
        //Поток-производитель не успевает добавить элемент , прежде чем поток-потребитель проверяет !names.isEmpty().
        //Из-за отсутствия синхронизации JVM может кэшировать состояние переменной names в каждом потоке — т.е. один поток видит "свою" версию списка, а другой — другую.
        //Возможны гонки данных (race condition), когда поведение программы становится непредсказуемым.

        //Когда ты вызываешь .start() на потоках подряд:
        // то порядок запуска потоков не гарантируется . JVM сама решает, когда какой поток начнёт выполняться. Иногда первым начинает потребитель — и тогда он видит список пустым.
        List<String> names = new ArrayList<>();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (!names.isEmpty()) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Обслужили покупателя " + names.remove(0));
                } else {
                    System.out.println("ПУСТО");
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                names.add("Имя номер " + i);

            }
        }).start();


    }
}
