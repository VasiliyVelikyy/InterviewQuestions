package sandbox.conf.puzzlers.ng_s04;

public class CompileTest {

    public static void main(String[] args) {

    }
    private void test1 () {
        for (;;) {;;}
    }

    // не компилируеться
    private void test2 () {
       // for (;;) ;;

    }
    private void test3 () {
            {;} for (;;) {;}
    }

    private void test4 () {
        ; for (;;) ;
    }

}
