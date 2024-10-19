package sandbox.conf.puzzlers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WhatPrint {
    public static void main(String[] args) {
        // task1();
        // task2();
        // task3();
        // task4();
        // task5();
        // task6();
        task7();
    }

    //что выведет код
    private static void task1() {
        List<String> list = new ArrayList<>();
        list.add("молоко");
        list.add("хлеб");
        list.add("колбаса");
        Stream<String> stream = list.stream();
        list.add("яйца, яйца ещё!");
        stream.forEach(System.out::println);
    }

    //что выведет код
    private static void task2() {
        List<String> list = new ArrayList<String>();
        list.add("молоко");
        list.add("хлеб");
        list.add("колбаса");
        list = list.subList(0, 2); //не надо колбасу!
        Stream<String> stream = list.stream();
        list.add("яйца, яйца ещё!");
        stream.forEach(System.out::println);
    }


    //В чём разница между строчками 1 и 2?
    public void killAll() {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        List<String> sentence = Arrays.asList("Казнить");
        ex.submit(() -> Files.write(Paths.get("Приговор.txt"), sentence)); // 1
        //ex.submit(() -> { Files.write(Paths.get("Приговор.txt"), sentence); }); // 2

    }

    private static void task3() {
        System.out.println(
                Stream.of(-3, -2, -1, 0, 1, 2, 3).max(Math::max).get()
        );
    }

    //Что произойдёт после выполнения?
    private static void task4() {
        Map<String, String> oldSchool = new HashMap<>() {{
            put("buildTool", "maven");
            put("lang", "java");
            put("IOC", "jee");
        }};

        Map<String, String> proper = new HashMap<>() {{
            put("buildTool", "gradle");
            put("lang", "groovy");
            put("IOC", "spring");
        }};

        oldSchool.replaceAll(proper::put);
        System.out.println("oldSchool");
        oldSchool.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("proper");
        proper.forEach((k, v) -> System.out.println(k + ": " + v));
    }


    //Одинаково ли сработают три способа?
    private static void task5() {
        List<String> list = Arrays.asList("Вронский", "Поезд", "Анна");
        Comparator<String> cmp = Comparator.nullsLast(Comparator.naturalOrder());
        System.out.println(Collections.max(list, cmp));
        System.out.println(list.stream().collect(Collectors.maxBy(cmp)).get());
        System.out.println(list.stream().max(cmp).get());
    }

    //а теперь?
    private static void task6() {
        List<String> list = Arrays.asList("Вронский", null, "Анна");
        Comparator<String> cmp = Comparator.nullsLast(Comparator.naturalOrder());
        System.out.println(Collections.max(list, cmp));
        System.out.println(list.stream().collect(Collectors.maxBy(cmp)).get()); //так как возвращаеться Optional и у него нал значение
        System.out.println(list.stream().max(cmp).get());
    }

// Что произойдёт?
    private static void task7() {
        List<String> list = new ArrayList<>(Arrays.asList("Arne", "Chuck", "Slay"));
        list.stream().forEach(x -> {
            if(x.equals("Chuck")) {
                list.remove(x);
            }
        });
    }

}
