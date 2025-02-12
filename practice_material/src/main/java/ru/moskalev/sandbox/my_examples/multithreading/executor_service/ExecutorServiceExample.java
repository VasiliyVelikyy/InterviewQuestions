package ru.moskalev.sandbox.my_examples.multithreading.executor_service;

import java.util.concurrent.*;

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

        ExecutorService taskExecutor = Executors.newFixedThreadPool(10);
        int c=0;
        while(c<10) {
            taskExecutor.execute(() -> {
                incrementCounter();
                incrementVolCounter();
                System.out.println("Another thread was executed= "+Thread.currentThread().getName());
            });

            c++;
        }

        taskExecutor.shutdown();
        try {
            taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }

        System.out.println("after 10 increment not volatile counter= " + counter);
        System.out.println("after 10 increment  volatile counter= " + volCounter);

        System.out.println("ScheduledExecutorService");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Callable<String> task = () -> {
            System.out.println(Thread.currentThread().getName());
            return Thread.currentThread().getName();
        };
        scheduledExecutorService.schedule(task, 10, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }


}
