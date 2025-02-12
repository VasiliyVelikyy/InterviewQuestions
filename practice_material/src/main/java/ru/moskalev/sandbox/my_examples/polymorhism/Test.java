package ru.moskalev.sandbox.my_examples.polymorhism;

public interface Test {
    static void method() {
        System.out.println("static method from Test interface");

    }
    default int sum(int a, int b){
        return sumAll(a, b);
    }
    default int sum(int a, int b, int c){
        return sumAll(a, b, c);
    }

    private int sumAll(int... values){
        int result = 0;
        for(int n : values){
            result += n;
        }
        return result;
    }
}
