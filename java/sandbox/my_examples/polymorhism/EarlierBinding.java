package sandbox.my_examples.polymorhism;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//то выведет?
 class EarlierBinding {
    public static void main(String[] args) {
        Collection collection = new HashSet();
        print(collection);
    }

    private static void print(Collection collection) {
        System.out.println("collection");
    }

    private static void print(Set collection) {
        System.out.println("set");
    }

    private static void print(HashSet collection) {
        System.out.println("hashSet");
    }
}
