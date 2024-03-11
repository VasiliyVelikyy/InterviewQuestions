package block1.lambda.functional_interface;

public class ExampleFunctionalInterface {

    @FunctionalInterface
    interface Functional<T1, T2, R> {
        R apply(T1 arg1, T2 arg2);
    }

    public static void main(String[] args) {
        int answer= ((Functional<Integer, Integer, Integer>) (arg1, arg2) -> arg1 + arg2).apply(1,3);
        System.out.println("Answer from (arg1, arg2) -> arg1 + arg2) is "+answer);

        //сложение arg1 + arg2 можно заменить на method reference
         answer= ((Functional<Integer, Integer, Integer>) Integer::sum).apply(1,3);

        //либо можно так вызвать
        Functional<Integer, Integer, Integer> funct=((arg1, arg2) -> arg1+arg2);
        System.out.println(funct.apply(1,3));
    }
}
