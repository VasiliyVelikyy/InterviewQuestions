package ru.moskalev.sandbox.my_examples.lambda.stream_api.small_examples.example2_grouping;

import ru.moskalev.sandbox.my_examples.lambda.stream_api.small_examples.utils.PrintUtil;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

//https://habr.com/ru/articles/348536/

public class Main {
    public static void main(String[] args) {
        Worker worker1 = new Worker("Vasia", 23, 30000, "Manager");
        Worker worker2 = new Worker("Petia", 36, 100000, "Boss");
        Worker worker3 = new Worker("Koliy", 19, 10000, "Manager");
        Worker worker4 = new Worker("Olia", 40, 150000, "Boss");
        Worker worker5 = new Worker("Nik", 50, 1000, "Uborhcik");

        List<Worker> workers = new ArrayList<>(Arrays.asList(worker1, worker2, worker3, worker4, worker5));

        //1. Группировка списка рабочих по их должности (деление на списки)
        Map<String, List<Worker>> groupingByPosition = workers
                .stream()
                .collect(groupingBy(Worker::getPosition));

        PrintUtil.printGroupingByPositionResult(groupingByPosition);


        //2. Группировка списка рабочих по их должности (деление на множества)
        workers.add(worker5);//добавляем дубликат да теста
        Map<String, Set<Worker>> groupingByPositionWithSet = workers
                .stream()
                .collect(groupingBy(Worker::getPosition, Collectors.toSet()));

        PrintUtil.printGroupingByPositionWithSet(groupingByPositionWithSet);
        workers.remove(workers.size() - 1); //удаляем тестовый дубликат

        //3. Подсчет количества рабочих, занимаемых конкретную должность
        Map<String, Long> countingByPosition = workers.stream()
                .collect(groupingBy(Worker::getPosition, Collectors.counting()));

        PrintUtil.printCountingByPosition(countingByPosition);

        //4. Группировка списка рабочих по их должности, при этом нас интересуют только имена
        Map<String,Set<String>> nameGroupingByPosition=workers
                .stream()
                .collect(groupingBy(Worker::getPosition,
                        mapping(Worker::getName,
                                Collectors.toSet())));

        PrintUtil.printNameGroupingByPosition(nameGroupingByPosition);

        //5. Расчет средней зарплаты для данной должности
        Map<String,Double>groupingByPositionAndEvSalary=workers
                .stream()
                .collect(groupingBy(Worker::getPosition,
                        Collectors.averagingInt(Worker::getSalary)));

        PrintUtil.printGroupingByPositionAndEvSalary(groupingByPositionAndEvSalary);

        //6. Группировка списка рабочих по их должности, рабочие представлены только именами единой строкой
        Map<String, String> groupingByPositionAndNameIsString=workers
                .stream()
                .collect(groupingBy(Worker::getPosition,
                        mapping(Worker::getName
                                ,Collectors.joining(", "))));

        PrintUtil.printGroupingByPositionAndNameIsString(groupingByPositionAndNameIsString);

        //7. Группировка списка рабочих по их должности и по возрасту.
        Map<String, Map<Integer, List<Worker>>> groupingByPositionAndAge = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition,
                        Collectors.groupingBy(Worker::getAge)));

        PrintUtil.printGroupingByPositionAndAge(groupingByPositionAndAge);
    }
}
