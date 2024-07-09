package sandbox.my_examples.multithreading.executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    static int counter = 1;
    static volatile int volCounter = 1;

    public static void incrementCounter() {
        counter++;
    }

    public static void incrementVolCounter() {
        volCounter++;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Создаем пул из 10 потоков


        executor.submit(() -> {
            // Задача, выполняемая в одном из потоков пула
            incrementCounter();
            incrementVolCounter();
            System.out.println("Асинхронная задача " + Thread.currentThread().getName());
        });

        executor.shutdown(); // Завершаем работу ExecutorService после выполнения всех задач

        System.out.println("after 10 increment not volatile counter= " + counter);
        System.out.println("after 10 increment  volatile counter= " + volCounter);
    }
}
