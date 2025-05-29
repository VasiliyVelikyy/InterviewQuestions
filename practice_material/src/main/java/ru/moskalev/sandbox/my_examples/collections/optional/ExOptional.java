package ru.moskalev.sandbox.my_examples.collections.optional;

import java.util.*;

public class ExOptional {
    private final static String FIRST_KEY ="first";
    private final static String SECOND_KEY ="second";
    public static void main(String[] args) {
        Map<String,List<Integer>>map = new HashMap<>();
        map.put(FIRST_KEY, Arrays.asList(1,2,3));
        map.put(SECOND_KEY, Arrays.asList(1,2,3));

        List<Integer> result = new ArrayList<>();

        // optional в том. что он не оптимизируется компилятором в отличие от if-ов
        //эти строчки лучше переписать
        Optional.ofNullable(map.get(FIRST_KEY)).ifPresent(result::addAll);
        Optional.ofNullable(map.get(SECOND_KEY)).ifPresent(result::addAll);


        //на эти строчки
        var first = map.get(FIRST_KEY);
        var second = map.get(SECOND_KEY);
        if(first != null) {
            result.addAll(first);
        }
        if(second != null) {
            result.addAll(second);
        }

        System.out.println(result);
    }
}
