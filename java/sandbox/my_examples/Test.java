package sandbox.my_examples;

public class Test {
    final Object LOCK=new Object();

    public  void method() {
        synchronized (LOCK){
            System.out.println("run this method");
        }
    }
}
