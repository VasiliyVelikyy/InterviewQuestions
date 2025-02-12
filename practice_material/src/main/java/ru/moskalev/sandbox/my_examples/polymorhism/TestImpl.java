package ru.moskalev.sandbox.my_examples.polymorhism;

public class TestImpl implements Test{
    int a;
    public static void main(String[] args) {
        Test.method();
       // a=1; //не присвоится так как стат метод не имеет доступа к нестат переменным
    }


}
