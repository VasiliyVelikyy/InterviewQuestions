package ru.moskalev.sandbox.conf.stream;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Метод который из листа листов, генерирует декартово произведение строк
 * input=asList(asList("a","b","c"),asList("x","y"),asList("1","2","3"))
 * -> ax1
 * -> ax2
 * -> ax3
 * -> ay1
 * -> ay2
 * ...
 * -> cy2
 * -> cy3
 */
public class StreamOfDecarts {

    //знаем что 3 элемента в списке
    public void generateFromThreeElem(List<List<String>> input) {
        Stream<String> stream =
                input.get(0).stream().flatMap(a ->
                        input.get(1).stream().flatMap(b ->
                                input.get(2).stream().map(c -> a + b + c)));
        stream.forEach(System.out::println);
    }

    //универсальное решение
    public void generate(List<List<String>> input) {
        Supplier<Stream<String>> s = input.stream()             //создаем стрим из внешнего списка
                //Stream<List<String>>                                //стрим списка строк
                .<Supplier<Stream<String>>>map(list -> list::stream) //каждый список мепим на саплаер стрима
                //Stream<Supplier<Stream<String>>>                  //стрим саплаеров стримов строк
                .reduce((sup1, sup2) -> () -> sup1.get()           //редюс на функция саплаеров. Делаем новый саплаер `-> () ->`
                        .flatMap(e1 -> sup2.get().map(e2 -> e1 + e2))) //
                //Optional<Supplier<Stream<String>>>             //редьюс возвращает Optional
                .orElse(() -> Stream.of(""));               // дефолтное поведение когда исходный список абсолютно пуст, выдаем стрим из 1 пустой строки
        s.get().forEach(System.out::println);
    }

    public static void main(String[] args) {
        StreamOfDecarts streamOfDecarts = new StreamOfDecarts();
        var input = asList(asList("a", "b", "c"), asList("x", "y"), asList("1", "2", "3"));

        System.out.println("generateFromThreeElem");
        streamOfDecarts.generateFromThreeElem(input);

        System.out.println("\n universal generate ");
        streamOfDecarts.generate(input);


    }
}
