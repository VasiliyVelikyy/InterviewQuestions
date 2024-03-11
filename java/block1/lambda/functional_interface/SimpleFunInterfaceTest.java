package block1.lambda.functional_interface;

public class SimpleFunInterfaceTest {
    public static void main(String[] args) {
        carryOutWork(new SimpleFuncInterface() {
            @Override
            public void doWork() {
                System.out.println("Do work in SimpleFun impl...");
            }
        });
        carryOutWork(() -> System.out.println("Do work in lambda exp impl...")); //эта строчка создание еще одного анонимного класса,в котором
        //реализуеться метод doWork но уже с надписью Do work in lambda exp impl...
    }
    public static void carryOutWork(SimpleFuncInterface sfi) {
        sfi.doWork();
    }
}
