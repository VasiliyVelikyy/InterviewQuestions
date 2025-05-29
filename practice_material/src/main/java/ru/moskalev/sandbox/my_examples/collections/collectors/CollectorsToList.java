package ru.moskalev.sandbox.my_examples.collections.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsToList {
    public static void main(String[] args) {
        exampleToArrayList();
        exampleToList();
    }


    static void exampleToArrayList() {
        Set<Integer> set = Set.of(6, 0, 4, 2, 5, 1, 7);
        ArrayList<Integer> arrayList = set
                .stream()
                .filter(i -> i > 3)
                //явно указываем что хотим ArrayList
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(arrayList.getClass().getSimpleName());
    }

    static void exampleToList() {
        Set<Integer> set = Set.of(6, 0, 4, 2, 5, 1, 7);
        //преимущественно будет ArrayList, но компилятор не даст указать его явно
        List<Integer> list = set.stream()
                .filter(i -> i > 3)
                .collect(Collectors.toList());

        System.out.println(list.getClass().getSimpleName());
    }
}
