package ru.moskalev.sandbox.interview_tasks.whats_print.one;

class Test {
    public static void main(String[] args) {
        int i = 5;
        i = i++ + ++i;
        System.out.println(i); //12

        Boolean b = new Boolean("/true"); //false
        System.out.println(b);

        //как правильно создать массив int
        int[] a = new int[]{1, 2, 3};
        int[] с = {1, 2, 3};

    }
}
