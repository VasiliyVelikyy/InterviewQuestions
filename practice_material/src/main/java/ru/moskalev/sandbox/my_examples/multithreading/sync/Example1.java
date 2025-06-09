package ru.moskalev.sandbox.my_examples.multithreading.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * Решение InitialProblem 1 способ
 * но это не гарантирует порядок запуска - у нас может первые выводы быть ПУСТО
 */
class Example1 {
    public static void main(String[] args) {


        List<String> names = new ArrayList<>();

        //добавим остановку чтобы успеть  наполнить
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                names.add("Имя номер " + i);
                System.out.println("Добавляем покупателя" + i);
            }
        }).start();


        //добавим остановку чтобы успеть проверить наполненность
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!names.isEmpty()) {
                    System.out.println("Обслужили покупателя " + names.remove(0));
                } else {
                    System.out.println("ПУСТО");
                }
            }
        }).start();




    }
}
