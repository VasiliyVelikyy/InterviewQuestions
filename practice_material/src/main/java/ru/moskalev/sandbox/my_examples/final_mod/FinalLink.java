package ru.moskalev.sandbox.my_examples.final_mod;

import java.util.ArrayList;
import java.util.Arrays;

class FinalLink {
    public static void main(String[] args) {
        final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(list);
        list.set(0, 5);
        System.out.println(list);
        //list=new ArrayList<>(); //ошибка компиляции
    }
}
