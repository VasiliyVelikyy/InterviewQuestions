package sandbox.my_examples.multithreading.cyclebarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class CyclicBarrierExample {
    public static void main(String[] args) throws InterruptedException {

        exampleWithoutAction();
        // exampleWithAction();

    }

    private static void exampleWithAction() {

        Runnable action = () -> System.out.println("На старт!");
        CyclicBarrier berrier = new CyclicBarrier(3, action);

        Runnable task = () -> {
            try {
                System.out.println("Process"); //особенность дебага многопоточки. Здесь будет 3 вывод строки сразу
                berrier.await();
                System.out.println("Finished");
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.println("Limit: " + berrier.getParties());
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }

    private static void exampleWithoutAction() {

        CyclicBarrier berrier = new CyclicBarrier(3);

        Runnable task = () -> {
            try {
                System.out.println("Process"); //особенность дебага многопоточки. Здесь будет 3 вывод строки сразу
                berrier.await();
                System.out.println("Finished");
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.println("Limit: " + berrier.getParties());
        for (int i = 0; i < 100; i++) { // если количество потоков больше чем в CyclicBarrierExample, то получаем что кто то выбивается из блокировки
            new Thread(task).start();
        }

    }
}
