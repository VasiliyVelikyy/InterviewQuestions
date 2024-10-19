package sandbox.conf.puzzlers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


public class WhatPrint {
    public static void main(String[] args) {
        // task1();
       // task2();
       task3();
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
    public void killAll(){
        ExecutorService ex = Executors.newSingleThreadExecutor();
        List<String> sentence = Arrays.asList("Казнить");
        ex.submit(() -> Files.write(Paths.get("Приговор.txt"), sentence) ); // 1
        //ex.submit(() -> { Files.write(Paths.get("Приговор.txt"), sentence); }); // 2

    }

    private static void task3() {
        System.out.println(
                Stream.of(-3, -2, -1, 0, 1, 2, 3).max(Math::max).get()
        );
    }
}
