package sandbox.courses.stepic.java_functional_programming_OLD_2017.four_functional_data_processin.nine;

/*4.9 The sum of odd numbers
0 из 1 шага пройдено
0 из 1 баллa  получено
Write a method for calculating the sum of odd numbers in the given interval (inclusively) using Stream API.

Important. Use the provided template for your method. Pay attention to type of an argument and the returned value. Please, don't use cycles.

Sample Input 1:

0 0
Sample Output 1:

0
Sample Input 2:

7 9
Sample Output 2:

16
Sample Input 3:

21 30
Sample Output 3:

125*/

import java.util.stream.LongStream;

public class TheSumOfOddNumbers {
    public static long sumOfOddNumbersInRange(long start, long end) {
        return (long) LongStream
                .range(start, end + 1)
                .asDoubleStream()
                .filter(elem -> isOddNumber(elem))
                .sum();
    }

    private static boolean isOddNumber(double elem) {
        return elem % 2 != 0;
    }

    public static void main(String[] args) {
        long result = sumOfOddNumbersInRange(7, 9);
        System.out.println(result);

        result = sumOfOddNumbersInRange(0, 0);
        System.out.println(result);
    }

}
