package ru.moskalev.sandbox.my_examples.statics;

class Base {
    public static void main(String[] args) {
        Base b = new Child();
        b.printMe(); // хоть объект Child напечатается Base
    }

    static void printMe() {
        System.out.println("Base");
    }
}

class Child extends Base {
    static void printMe() {
        System.out.println("Child");
    }
}
