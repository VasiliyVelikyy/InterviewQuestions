package block1.lambda.callback;

public class ExampleTwo {
    public static void main(String[] args) throws  Exception {

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
