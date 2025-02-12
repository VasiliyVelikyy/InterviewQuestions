package ru.moskalev.sandbox.my_examples.lambda.functional_interface;

@FunctionalInterface
public interface ComplexFunctionalInterface extends SimpleFuncInterface {
    default  void doSomeWork() {
        System.out.println("Doing some work in interface impl...");
    }

    private void privateMethod(){
        System.out.println("Private method");
    }

    @Override
    default void defMethodParnet() {
        System.out.println("overide defMethodParnet");
    }
}
