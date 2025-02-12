package ru.moskalev.sandbox.my_examples.generics.pecs;

import java.util.ArrayList;
import java.util.List;

public class PECSExample {
    public static void main(String[] args) {
        //  exampleTypeCasting();

        //extends - коллекция может состоять из объектов типа Number и всех его подтипов
        //extends — это producers
        List<? extends Number> producer = new ArrayList<>();
        addElements1(producer);

        //super - коллекция может иметь объекты типа Integer и всех супертипов — например, Number или Object
        //super - это consumer
        List<? super Integer> consumer = new ArrayList<>();
        addElements2(consumer);

    }

    private static void exampleTypeCasting() {
        String str = new String("Test!");

        Object obj = str;//все ок

        List<String> strings = new ArrayList<String>();

        // List<Object> objects = strings; //не ок

    }


    public static void addElements1(List<? extends Number> list) {
        Number number = list.get(0); // это работает
        // list.add(1);// ошибка компиляции потому что он не знает точный тип элементов списка.
    }

    public static void addElements2(List<? super Integer> list) {
        // Number number = list.get(0); // ошибка компиляции потому что он не знает точный тип элементов списка
        list.add(1); // это работает
    }
}
