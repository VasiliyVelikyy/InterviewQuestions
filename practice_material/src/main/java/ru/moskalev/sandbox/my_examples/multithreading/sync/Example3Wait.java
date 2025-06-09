package ru.moskalev.sandbox.my_examples.multithreading.sync;

import java.util.ArrayList;
import java.util.List;

public class Example3Wait {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (names) {
                    if (names.isEmpty()) {
                        try {
                            //останавливает выполнение текущего потока до тех пор, пока другой поток не вызовет notify() или notifyAll()
                            names.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Обслужили покупателя " + names.remove(0));
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (names) {
                    names.add("Имя номер " + i);
                    System.out.println("Добавляем покупателя" + i);
                    names.notify();
                }
            }
        }).start();
    }
}
