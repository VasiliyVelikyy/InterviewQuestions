package sandbox.my_examples.multithreading.cyclebarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

class CyclicBarrierExample2 {
     public static void main(String[] args) {
         CyclicBarrier b1=new CyclicBarrier(5, ()-> System.out.println("==== 1 этап закончен ===="));
         CyclicBarrier b2=new CyclicBarrier(5, CyclicBarrierExample2::run);
         IntStream.range(0,10).forEach((i)-> // если количество потоков больше чем в CyclicBarrier. То мы можем получить что кто то выбивается
                 new Thread(new Worker(b1,b2)).start());
     }

    private static void run() {
        System.out.println("==== 2 этап закончен ====");
    }
}
