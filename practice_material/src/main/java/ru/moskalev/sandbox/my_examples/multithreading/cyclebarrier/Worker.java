package ru.moskalev.sandbox.my_examples.multithreading.cyclebarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Worker implements Runnable{
    private final CyclicBarrier b1;
    private final CyclicBarrier b2;
    public Worker(CyclicBarrier b1, CyclicBarrier b2){
        this.b1=b1;
        this.b2=b2;
    }
    @Override
    public void run() {

        try {
            System.out.println("оторвать");
            b1.await();
            System.out.println("намазать");
            b2.await();
            System.out.println("приклеить");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}