package sandbox.courses.stepic.java_functional_programming_OLD_2017.four_functional_data_processin.ten;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//4.10
public class UnderstandingOfFlatMap {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());

       // a) [1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5]
        List<Integer> generated1 = numbers.stream()
                .flatMap(n -> Stream.generate(() -> n).limit(n))
                .collect(Collectors.toList());
       // b) [1, 1, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 5]
        List<Integer> generated2 = numbers.stream()
                .flatMapToInt(n -> IntStream.rangeClosed(1, n))
                .boxed()
                .collect(Collectors.toList());
        //c)[1, 2, 3, 3, 4, 5, 4, 5, 6, 7, 5, 6, 7, 8, 9]
        List<Integer> generated3 = numbers.stream()
                .flatMapToInt(n -> IntStream.iterate(n, val -> val + 1).limit(n))
                .boxed()
                .collect(Collectors.toList());
        //d)[1, 2, 3, 4, 5]
        List<Integer> generated4 = numbers.stream()
                .flatMap(Stream::of)
                .collect(Collectors.toList());

        System.out.println("generated1= "+generated1);
        System.out.println("generated2= "+generated2);
        System.out.println("generated3= "+generated3);
        System.out.println("generated4= "+generated4);

    }


}
