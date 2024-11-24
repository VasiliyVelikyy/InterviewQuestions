package sandbox.my_examples.polymorhism;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//то выведет?
class EarlierBinding {
    public static void main(String[] args) {
        Collection collection = new HashSet();
        statPrint(collection); //раннее связывание

//        EarlierBinding earlier = new EarlierBinding();
//        earlier.print(collection); // позднее связывание
    }


    private static void statPrint(Collection collection) {
        System.out.println("collection");
    }

    private static void statPrint(Set collection) {
        System.out.println("set");
    }

    private static void statPrint(HashSet collection) {
        System.out.println("hashSet");
    }

//    public void print(Collection collection) {
//        System.out.println("collection");
//    }

//    public void print(Set collection) {
//        System.out.println("set");
//    }
//
//    public void print(HashSet collection) {
//        System.out.println("hashSet");
//    }

}
