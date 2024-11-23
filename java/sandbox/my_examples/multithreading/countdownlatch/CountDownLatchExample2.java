package sandbox.my_examples.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

class CountDownLatchExample2 {
     public static void main(String[] args) throws InterruptedException {
         CountDownLatch countDownLatch=new CountDownLatch(10);
         IntStream.range(0,10).forEach((i)->
                 new Thread(new TestAndGo(countDownLatch)).start());
         countDownLatch.await();
         System.out.println("все сдали, зачет окончен");
     }
}
