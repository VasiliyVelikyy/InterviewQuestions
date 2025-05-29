package ru.moskalev.sandbox.my_examples.collections.stream;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamEx {
    public static void main(String[] args) {
        // из массива сделать мапу, где ключ- индекс массива а значение- элемент массива
        String[] array = "Казань Москва Питер".trim().split(" ");
        Map<Integer, String> cityMap = IntStream
                .range(0, array.length) // здесь создаеться поток примитивов инт
                .boxed() //нужен для того что Collectors работает только с объектами Integer
                .collect(Collectors.toMap(
                        i -> i,
                        i -> array[i]
                ));

        System.out.println(cityMap);
    }
}
