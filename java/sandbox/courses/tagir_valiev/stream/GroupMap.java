package sandbox.courses.tagir_valiev.stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * преобразовать List<Map<K,V> В Map<K,List<V>>
 * <p>
 * //output
 * {
 * a=[1,3,5],
 * b=[2,6],
 * d=[4],
 * e=[5,7]
 * }
 */
public class GroupMap {
    public static void main(String[] args) {
        Map<String, String> myMap1 = new HashMap<>() {{
            put("a", "1");
            put("b", "2");
        }};

        Map<String, String> myMap2 = new HashMap<>() {{
            put("a", "3");
            put("d", "4");
            put("e", "5");
        }};

        Map<String, String> myMap3 = new HashMap<>() {{
            put("a", "5");
            put("b", "6");
            put("e", "7");
        }};
        GroupMap groupMap = new GroupMap();
        groupMap.group(asList(myMap1, myMap2, myMap3))
                .forEach((key, value) -> System.out.println(key + " : " + value));
    }

    public Map<String, List<String>> group(List<Map<String, String>> input) {

        return input.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }
}
