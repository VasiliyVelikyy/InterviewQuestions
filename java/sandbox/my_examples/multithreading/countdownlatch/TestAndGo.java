package sandbox.my_examples.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;

class TestAndGo implements Runnable {
    private final CountDownLatch countDownLatch;
    public TestAndGo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("в процессе тестирования");
        countDownLatch.countDown();
        System.out.println("пошел домой");
    }
}