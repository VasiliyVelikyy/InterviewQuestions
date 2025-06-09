package ru.moskalev.sandbox.my_examples.multithreading.deadlock;

 class DeadlockExample {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Поток 1: Захвачен lock1");
                try {
                    Thread.sleep(100); // небольшая задержка для переключения потока
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 1: Пытаюсь захватить lock2");
                synchronized (lock2) {
                    System.out.println("Поток 1: Захвачен lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Поток 2: Захвачен lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 2: Пытаюсь захватить lock1");
                synchronized (lock1) {
                    System.out.println("Поток 2: Захвачен lock1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}