package sandbox.conf.puzzlers;

import java.util.function.Supplier;

public class SupplierExample {
    String str;
    void run() {
        str = "привет";
        Supplier<String> s1 = str::toUpperCase;
        Supplier<String> s2 = () -> str.toUpperCase();
        str = "hello";
        System.out.println(s1.get());
        System.out.println(s2.get());
    }

    public static void main(String[] args) {
        new SupplierExample().run();
    }
}
