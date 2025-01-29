package sandbox.interview_tasks.whats_print.one;

import java.util.ArrayList;
import java.util.List;

public class Test6 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        list.stream().forEach(s -> {
            System.out.println(s);
            list.add(s + " New");
        });
        //1,2,3,4 затем ConcurrentModificationException
        //Исключение ConcurrentModificationException возникает не сразу после начала итерации,
        // а только тогда, когда поток (stream) обнаруживает, что коллекция была изменена (в данном случае, добавлены новые элементы)
        // во время выполнения.
    }
}
