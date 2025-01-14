package sandbox.interview_tasks.whats_print;

import java.util.*;

class CollectionPrint {
    public static void main(String[] args) {
        printHashSet();

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
        Set<String> leaders =new HashSet<>(Arrays.asList("Stalin","Lenin","Che Gevara"));
        System.out.println("leaders= "+leaders.toString());
    }

    //Что произойдет и почему
    private static void listWhatHappened(){
        List<String> list=new ArrayList<>(Arrays.asList("A","B","C"));

        list.stream().forEach(x->{
            if(x.equals("C")){
                list.remove(x);
            }
        });
    }

    // А в этом случае?
    private static void listWhatHappened2(){
        List<String> list=new ArrayList<>(Arrays.asList("A","B","C"));

        list.stream().forEach(x->{
            if(x.equals("A")){
                list.remove(x);
            }
        });
    }

}
