package ru.moskalev.sandbox.my_examples.multithreading.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Еще один вариант синхронизации потоков
 * аналог Example1
 */
class ExCountDownLatch {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1); // ожидаем один сигнал

// Потребитель
        new Thread(() -> {
            try {
                latch.await(); // ждём сигнала от производителя
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                if (!names.isEmpty()) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Обслужили покупателя " + names.remove(0));
                } else {
                    System.out.println("ПУСТО");
                }
            }
        }).start();

// Производитель
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Добавляем покупателя" + i);
                names.add("Имя номер " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    return;
                }
            }
            latch.countDown(); // даём сигнал, что закончили подготовку
        }).start();
    }
}
