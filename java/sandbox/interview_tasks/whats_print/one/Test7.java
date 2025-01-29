package sandbox.interview_tasks.whats_print.one;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Test7 {
    public static void main(String[] args) {
        String[] names = {"Java", "Kotlin", "Java"};
        String name = "Java";
        Predicate predicate = name::equals;

        long count1 = Stream.of(names).filter(predicate).count();
        System.out.println("count1=" + count1);
        name = "Kotlin";

        long count2 = Stream.of(names).filter(predicate).count();//у predicate не меняется ссылка, в отличие от name
        System.out.println("count2=" + count2);
    }
}
