package ru.moskalev.sandbox.my_examples.lambda.stream_api.small_examples.example3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StringCounts {
    public static void main(String[] args) {
        String s = "s s str werr s str"; // найти вхождение каждой подстроки. в результат вывести строка и количество

        Map<String, Long> result1 = Stream.of(s.split(" ")).collect(groupingBy(identity(), counting()));
        System.out.println(result1);

        List<String> items = Arrays.asList("apple", "apple", "banana",
                "apple", "orange", "banana", "papaya");

        Map<String, Long> result2 =
                items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(result2);
    }
}
