package sandbox.conf.puzzlers.ng_s04;

import java.util.HashSet;
import java.util.Set;

public class CharTest {
    public static void main(String[] args) {
        Set<Character> set = new HashSet<>();
        for(char ch = 'а'; ch <= 'я'; ch++) {
            set.add(ch);
            char test= (char) (ch - 1);
            set.remove(test);
        }
        System.out.println(set.size());
    }
}
