package sandbox.my_examples.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestListCount {
    public static void main(String[] args) {
        //List<Integer> list = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        try {
            while (true) {
                list.add(new Integer(1));
            }
        } catch (Throwable e) {
            System.out.println("error");
            System.out.println(list.size());
        }
    }

}
