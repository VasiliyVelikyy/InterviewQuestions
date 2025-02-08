package sandbox.interview_tasks.whats_print;

import java.util.*;

class CollectionPrint {
    public static void main(String[] args) {
        printHashSet();
//        listWhatHappened();
//        listWhatHappened2();
//        listWhatHappened3();
//        listWhatHappened4();
        rightWayForRemove();
    }

    //что выведет?
    private static void printHashSet() {
        HashSet<String> set = new HashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("cherry");

        System.out.println(set.toString()); // при передаче в метод println можно не вызывать toString потому что внутри
        // вызывается valueOf -> toString у объекта если он не null
        //toString() у объекта HashSet не переопределён,
        // он будет использовать реализацию, предоставленную классом AbstractCollection, от которого HashSet наследуется

        //то же самое
        Set<String> leaders = new HashSet<>(Arrays.asList("Stalin", "Lenin", "Che Gevara"));
        System.out.println("leaders= " + leaders.toString());
        //если у объекта не предопределен toString() то возвращается строка в виде: MyClass@1b6d3586  где MyClass- это имя класса объекта.
        // @ — разделитель.
        //1b6d3586 — хэшкод объекта в шестнадцатеричном формате, возвращаемый методом hashCode().
    }

    //Что произойдет и почему
    private static void listWhatHappened() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        list.stream().forEach(x -> {
            if (x.equals("C")) {// при удалении 3 элемента -> ConcurrentModificationException
                list.remove(x); //внутри этого метода есть проверка на счетчик начальной коллекции
                // и измененной  if (modCount != expectedModCount) throw new ConcurrentModificationException()
            }
        });
    }

    //NullPointerException - потому что
    //stream().forEach - проверяет ConcurrentModification, в данном случае для ArrayList, только в самом конце прохода по
    //элементов. Это сделано для скорости. Поэтому до ConcurrentModificationException мы не доходим.
    //Но вместо этого при удалении элемента массив не уменьшается. Элементы двигаются и вместо удаленного элемента
    //дописывается null в конце
    private static void listWhatHappened2() {
        List<String> list = new ArrayList<>(Arrays.asList("Arne", "Chuck", "Slay"));
        list.stream().forEach(x -> {
            if (x.equals("Chuck")) { // при удалении 1 и 2 элементов -> NullPointerException
                list.remove(x);
            }
        });
    }

    // А в этом случае?
    private static void listWhatHappened3() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        list.stream().forEach(x -> {
            if (x.equals("A")) {
                list.remove(x);
            }
        });
    }

    private static void listWhatHappened4() {
        List<String> list = new ArrayList<>(Arrays.asList("Arne", "Chuck", "Slay"));

        for (String x : list) {
            if (x.equals("Chuck")) { // если удалить предпоследний элемент, исключения не будет
                list.remove(x); // потому что итерация сразу уменьшиться на 1 и до сравнения со Slay не дойдет
            }
        }

        list = new ArrayList<>(Arrays.asList("Arne", "Chuck", "Slay"));

        for (String x : list) {
            if (x.equals("Slay")) { // если удалить 3 или 1 элемент, будет ConcurrentModificationException
                list.remove(x);
            }
        }
    }

    private static void rightWayForRemove() {
        // все удаления нужно делать через итератор либо через методы стрима
        List<String> list = new ArrayList<>(Arrays.asList("Arne", "Chuck", "Slay"));
        list.removeIf(x -> x.equals("Chuck")); //Внутренне removeIf  использует Iterator для итерации по списку и сопоставления элементов с помощью предиката.

        list = new ArrayList<>(Arrays.asList("Arne", "Chuck", "Slay"));
        var iter = list.iterator();

        while (iter.hasNext()) {
            if (iter.next().equals("Chuck")) {
                iter.remove();
            }
        }
    }

}
