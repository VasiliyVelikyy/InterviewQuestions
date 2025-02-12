package ru.moskalev.sandbox.interview_tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class DuplicateCount {
    //[1, 2, 3, 3, 3, 4, 5, 6, 6]    //3
    public static void main(String[] args) {

        int result = countDuplicate(new int[]{1, 2, 3, 3, 3, 4, 5, 6, 6});
        System.out.println(result);

        result = countDuplicate2(new int[]{1, 2, 3, 3, 3, 4, 5, 6, 6});
        System.out.println(result);

        result = countDuplicate3(new int[]{1, 2, 3, 3, 3, 4, 5, 6, 6});
        System.out.println(result);

    }

    //В СТРИМЕ НО ЕСТЬ МЕТОД ДИСТИНКТ.
    //ПОПРОБОВАТЬ С КАУНТОМ
    static int countDuplicate(int[] array) {
        /*
         если из примитимвного массива делать список- то ничего не получиться. Нужен массив Integers
         Работает
         Integer[] ints = new Integer[] {1,2,3,4,5};
         List<Integer> list = Arrays.asList(ints);

          не работает
          ArrayList<Integer> list=Arrays.asList(array); */
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if (list.contains(array[i]))
                count++;
            else list.add(array[i]);
        }
        return count;
    }

    static int countDuplicate2(int[] array) {
        HashSet<Integer> set = Arrays.stream(array)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));
        return array.length - set.size();
    }

    static int countDuplicate3(int[] array) {
        int distCount = (int) Arrays.stream(array).distinct().count();
        return array.length - distCount;
    }


}
