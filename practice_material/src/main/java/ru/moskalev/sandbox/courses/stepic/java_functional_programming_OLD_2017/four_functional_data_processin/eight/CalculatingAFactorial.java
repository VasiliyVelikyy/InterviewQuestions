package ru.moskalev.sandbox.courses.stepic.java_functional_programming_OLD_2017.four_functional_data_processin.eight;

/*4.8 Calculating a factorial
0 из 1 шага пройдено
0 из 1 баллa  получено
Many java developers wrote methods to calculate a factorial value using a recursive or iterative algorithm. It's time to do it with streams.

The factorial of n is calculated by the product of integer number from 1 to n (inclusive). The factorial of 0 is equal to 1.

Important. Use the given template for your factorial method. Pay attention to type of an argument and the returned value. Please, don't use cycles or recursion.

Sample Input 1:

0
Sample Output 1:

1
Sample Input 2:

1
Sample Output 2:

1
Sample Input 3:

2
Sample Output 3:

2*/

import java.util.stream.LongStream;

public class CalculatingAFactorial {

    /**
     * Calculates the factorial of the given number n
     *
     * @param n >= 0
     * @return factorial value
     */
    public static long factorial(long n) {
        return (long) LongStream
                .range(1, n + 1)
                .asDoubleStream()
                .reduce(1, (a, b) -> a * b);
    }

    public static void main(String[] args) {
        long result = factorial(5); //120
        System.out.println(result);

        result = factorial(1); //1
        System.out.println(result);
    }

}
