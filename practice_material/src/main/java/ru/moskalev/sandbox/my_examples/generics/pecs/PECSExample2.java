package ru.moskalev.sandbox.my_examples.generics.pecs;

import java.util.ArrayList;
import java.util.List;

public class PECSExample2 {
    public static void main(String[] args) {
        PECSExample2 test = new PECSExample2();
        List<? super Paper> consumer = new ArrayList<>();
        List<? extends Paper> producer = new ArrayList<>();
        test.testUpperBounding(producer);
        test.testLowBounding(consumer);
    }

    public void testUpperBounding(List<? extends Paper> list) {
        Paper p = list.get(0); // OK
        Garbage g = list.get(1); // OK
        // CoolPaper sp = list.get(2); // не скомпилируется
        //list.add(new Paper()); // не скомпилируется
        list.add(null); // OK
    }

    //Вторая часть принципа PECS означает, что из коллекций, ограниченных снизу,
    // нельзя без явного приведения типа (cast) прочитать объекты граничного класса, да и всех его родителей тоже.
    // Единственное, что доступно, — тип Object:
    public void testLowBounding(List<? super Paper> list) {
        // Paper p = list.get(0); // не скомпилируется
        //Garbage g = list.get(1); // не скомпилируется
        Object o = list.get(2); // OK
        //list.add(new Garbage()); // не скомпилируется
        list.add(new Paper()); // OK
        list.add(new CoolPaper()); // OK
    }

    class Garbage {
    }

    class Paper extends Garbage {
    }

    class CoolPaper extends Paper {
    }
}
