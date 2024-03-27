package sandbox.my_examples.lambda.stream_api.small_examples.example3;

public class Sorting {
//    public static void main(String[] args) {
//        //3 apple, 2 banana, others 1
//        List<String> items = Arrays.asList("apple", "apple", "banana",
//                "apple", "orange", "banana", "papaya");
//
//        Map<String, Long> result = items.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
//
//        System.out.println("counting result= " + result);
//
//        /* потому что хешмапа хранит данные хоотично по бакетам, поэтому нужен связанный список куда добавим данные
//         Map<String, Long> finalMap = new LinkedHashMap<>();
//        Sort a map and add to finalMap
//        result.entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue()
//                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
//
//        System.out.println(finalMap);
//        *
//        * */
//
//        Map<String, Long> treeMap = result.entrySet()
//                .stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                .collect(toTreeMap(pair -> pair.getKey(), pair -> pair.getValue()));
//
//        System.out.println("sorting result= " + finalMap);
//
//    }
}
