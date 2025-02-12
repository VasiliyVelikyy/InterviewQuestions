package ru.moskalev.sandbox.conf.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

/**
 * Обработать перекрыв пары из входящего потока
 * Вывод: A->B, B->C, C->D
 */
public class OverlappingPairs {
    public static void main(String[] args) {
        OverlappingPairs overlappingPairs = new OverlappingPairs();
        System.out.println(overlappingPairs.process(asList("A", "B", "C", "D")));
    }

    public String process(List<String> list) {
        return IntStream.range(0, list.size() - 1)
                .mapToObj(i -> list.get(i) + "->" + list.get(i + 1))
                .collect(Collectors.joining(","));
    }
}
