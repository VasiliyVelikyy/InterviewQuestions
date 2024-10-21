package sandbox.my_examples;

public class StringExampleOne {
    public static void main(String[] args) {
        String str1 = "TopJava";
        String str2 = "TopJava";
        String str3 = new String("TopJava");

        System.out.println("Строка 1 равна строке 2? " + (str1 == str2));
        System.out.println("Строка 2 равна строке 3? " + (str2 == str3));
    }
}
