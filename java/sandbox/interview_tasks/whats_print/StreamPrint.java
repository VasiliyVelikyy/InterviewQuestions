package sandbox.interview_tasks.whats_print;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//что будет выведено на экран
class StreamPrint {
    public static void main(String[] args) {
       // printIntStream();
        printStream();
    }

    //здесь свалится javaheapspase потому что идет бесконечная генерация стрима
    // чтобы заработало, нужно .sorted() и limit поменять местами
    private static void printIntStream() {
        IntStream.iterate(1, i -> i + 1) // начало с 1, увеличение на 1 последующий элемент
                .sorted()
                .limit(5)
                .forEach(System.out::println);
    }

   // Ответ- lateBinding - все изменения в стриме проигрываются только тогда, когда происходит терминальная операция
    private static void printStream() {
        List<String> list = new ArrayList<String>();
        list.add("молоко");
        list.add("хлеб");
        list.add("колбаса");
        Stream<String> stream = list.stream();//
        list.add("яйца, яйца ещё!");
        stream.forEach(System.out::println);
    }
}
