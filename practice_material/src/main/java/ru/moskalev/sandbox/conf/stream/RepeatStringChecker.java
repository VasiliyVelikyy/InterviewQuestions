package ru.moskalev.sandbox.conf.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Оставить значения которые повторяются не менее n раз
 */
public class RepeatStringChecker {

    public void check(List<String> list) {
        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        map.values().removeIf(e -> e < 3);
        map.keySet().forEach(System.out::println);
    }


    public static void main(String[] args) {
        RepeatStringChecker checker = new RepeatStringChecker();
        checker.check(Arrays.asList("Jpoint", "Joker", "Jbreak", "Jbreak", "Joker", "Jbreak"));
    }
}
