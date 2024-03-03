package block1.collections.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Person person1 = new Person(20, "Вася");
        Person person2 = new Person(19, "Коля");
        Person person3 = new Person(32, "Петя");
        Person person4 = new Person(15, "Ая");
        Person person5 = new Person(65, "Яна");

         Map<Integer,Person> treeMapByInt=new TreeMap<>();//при такой реализации элементы будут отссортированы в зависимости от ключа
        //у объекта Ключа должен реализован быть метод compareTo
        treeMapByInt.put(person1.age,person1);
        treeMapByInt.put(person2.age,person2);
        treeMapByInt.put(person3.age,person3);
        treeMapByInt.put(person4.age,person4);
        treeMapByInt.put(person5.age,person5);
        System.out.println("print treeMapByInt");
        treeMapByInt.entrySet().forEach(pair -> System.out.println("key: " + pair.getKey() + ", value: " + pair.getValue().name));


        //если реализовывать сравнение ключа которое являеться объектом то нужен компаратор
        Map<Person,Integer> treeMapByPersonName = new TreeMap<>(Comparator.comparing(o -> o.name));

        treeMapByPersonName.put(person1,1);
        treeMapByPersonName.put(person2,2);
        treeMapByPersonName.put(person3,3);
        treeMapByPersonName.put(person4,4);
        treeMapByPersonName.put(person5,5);
        System.out.println("print treeMapByPersonName");
        treeMapByPersonName.entrySet().forEach(pair -> System.out.println("key: " + pair.getKey().name + ", value: " + pair.getValue()));


    }
}
