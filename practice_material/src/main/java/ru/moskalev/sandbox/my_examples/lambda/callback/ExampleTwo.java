package ru.moskalev.sandbox.my_examples.lambda.callback;

public class ExampleTwo {
    public static void main(String[] args) {
        // интерфейс Callback можно написать лямдой
        //new ExampleTwo().doWork(() -> System.out.println("callback called"));// implementing class

        new ExampleTwo().doWork(new Callback() { // implementing class
            @Override
            public void call() {
                System.out.println("callback called");
            }
        });
    }

    public void doWork(Callback callback) {
        System.out.println("doing work");
        callback.call();
    }


}
