package ru.moskalev.sandbox.my_examples.multithreading.sync;

import java.util.ArrayList;
import java.util.List;

/*Пример как Example1, но используем объект для синхронизации*/
class Example2 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        Object lock = new Object(); // Объект для синхронизации

        //Поток-потребитель:
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {//вместо lock можно использовать names
                    if (!names.isEmpty()) {
                        System.out.println("Обслужили покупателя " + names.remove(0));
                    } else {
                        System.out.println("ПУСТО");
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();

        //Поток-производитель:
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    names.add("Имя номер " + i);
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();
    }
}
