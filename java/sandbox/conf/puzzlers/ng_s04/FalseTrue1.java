package sandbox.conf.puzzlers.ng_s04;

public class FalseTrue1 {
    public static void main(String[] args) {
        final boolean rick;
        if (true && (rick = true)) {
        }
        if (false && (rick = false)) {
        }
        System.out.println(rick);
    }
}
