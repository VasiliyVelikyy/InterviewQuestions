package ru.moskalev.sandbox.conf.puzzlers.ng_s04;

import java.util.HashSet;
import java.util.Set;

public class CharTest {
    public static void main(String[] args) {
     //  test1();
       test2();
    }


    private static void test1() {
        Set<Character> set = new HashSet<>();
        for(char ch = 'а'; ch <= 'я'; ch++) {
            set.add(ch);
            char test= (char) (ch - 1);
            set.remove(test);
        }
        System.out.println(set.size());
    }
    private static void test2() {
        Set<Character> set = new HashSet<>();
        for(char ch = 'а'; ch <= 'я'; ch++) {
            set.add(ch);
            var removeElem = (ch - 1);
            set.remove(removeElem);
        }
        System.out.println(set.size());
    }

}
