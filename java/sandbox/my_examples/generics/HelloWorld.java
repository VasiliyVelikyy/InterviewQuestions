package sandbox.my_examples.generics;

import java.util.*;
 class HelloWorld{
    public static void main(String []args) {
        List<String> list = Arrays.asList("Hello", "World");
        List<Integer> data = new ArrayList(list);
        Integer intNumber = data.get(0);//на этапе компиляции мы не увидели ошибку,а получили в рантайме
        System.out.println(data);
    }
}