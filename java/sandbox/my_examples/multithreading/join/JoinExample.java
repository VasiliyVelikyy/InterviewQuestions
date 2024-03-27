package sandbox.my_examples.multithreading.join;

/**
 * При написании многопоточных программ могут быть случаи, когда необходимо ждать завершения выполнения какого-либо потока и
 *  после этого продолжить выполнение текущего потока. В таких случаях полезно применять метод — join().
 *  Данный метод позволяет одному потоку ожидать завершения другого.
 *  */
public class JoinExample {
    public static void main(String[] args) {
        var threadTwo = new Thread(() -> {
            try {
                Thread.sleep(2000);
                int counter = 0;
                for (int i = 0; i < 1000; i++) {
                    counter ++;
                }
                var thread = Thread.currentThread().getName();
                System.out.println(thread + " has finished its execution, counter = " + counter);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }, "Counter thread");
        threadTwo.start();
        System.out.println("Main method executing");
    }
}
