package ru.moskalev.sandbox.my_examples.multithreading.sync;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Пример на подобие Example1
 */
class ExBlockingQueue {
    public static void main(String[] args) {
        /*BlockingQueue — это интерфейс из пакета java.util.concurrent, который представляет собой потокобезопасную очередь с блокирующими операциями .

            Он идеально подходит для ситуаций, где:

            Один или несколько потоков добавляют элементы (производители),
            Другие потоки их забирают и обрабатывают (потребители).
            метод        что делает                                           если очередь пустая/полная
            put(E e) -  Добавляет элемент в очередь                           Блокирует поток, пока не освободится место
            take()    Удаляет и возвращает элемент из очереди                 Блокирует поток, пока элемент не появится
            offer(E e, timeout, unit) Пытается добавить элемент с таймаутом   Возвращает false если время истекло
            poll(timeout, unit) Пытается получить элемент с таймаутом          Возвращает null если время истекло
        * */


        /* Почему BlockingQueue хороша:
✅ Безопасность в многопоточной среде
Все операции синхронизированы.
Не нужно вручную использовать synchronized или wait/notify.
✅ Блокировка при необходимости
put() ждёт, пока в очереди освободится место.
take() ждёт, пока появится новый элемент.
Это избавляет от необходимости писать циклы проверки (while (isEmpty())) и использовать sleep() впустую.
LinkedBlockingQueue
На основе связного списка. Можно указать максимальный размер.
ArrayBlockingQueue
На основе массива. Имеет фиксированный размер.
PriorityBlockingQueue
Очередь с приоритетом (элементы упорядочены).
SynchronousQueue
Очередь без ёмкости. Производитель ждёт, пока потребитель не заберёт элемент.*/
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Поток-потребитель
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String customer = queue.take(); // ждёт, пока не будет элемент
                    System.out.println("Обслужили покупателя: " + customer);
                    Thread.sleep(300); // имитируем работу
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();

// Поток-производитель
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String name = "Имя номер " + i;
                    queue.put(name); // ждёт, пока будет место
                    System.out.println("Добавили покупателя: " + name);
                    Thread.sleep(300); // имитируем поступление
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();
    }
}
