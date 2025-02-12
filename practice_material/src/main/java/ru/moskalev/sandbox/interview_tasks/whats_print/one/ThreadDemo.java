package ru.moskalev.sandbox.interview_tasks.whats_print.one;

//Что выведет программа
public class ThreadDemo {
    private int count = 1;

    public synchronized void doSomething() {
        for (int i = 0; i < 10; i++) {
            System.out.println(count++);
        }
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        Thread thread1 = new A(demo);
        Thread thread2 = new A(demo);
        thread1.start();
        thread2.start();
    }
}

class A extends Thread {
    ThreadDemo demo;

    public A(ThreadDemo td) {
        demo = td;
    }

    public void run() {
        demo.doSomething();
    }
}
