package sandbox.my_examples.multithreading.executor_service;

import java.util.concurrent.Callable;

public class DelayedCallable implements Callable {
    String name;
    int i;

    public DelayedCallable(String name, int i) {
        this.name = name;
        this.i = i;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(name);
        return null;
    }
}
