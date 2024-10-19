package sandbox.conf.puzzlers.ng_s04;

public class VarExample {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        final var targaryens = 2;
        final var lannisters = 25;
        final var starks = '1';
        final var snow = true ? targaryens * lannisters : starks;
        //int test = true ? 50 : starks;
        //System.out.println(test);
        System.out.println(snow);
    }

    private static void test2() {
        var targaryens = 2;
        final var lannisters = 25;
        final var starks = '1';
        final var snow = true ? targaryens * lannisters : starks;
        System.out.println(snow);
    }

    private static void test3() {
        final int targaryens = 2;
        final int lannisters = 25;
        final char starks = '1';
        final char snow = true ? targaryens * lannisters : starks;
        System.out.println(snow);
    }

}
