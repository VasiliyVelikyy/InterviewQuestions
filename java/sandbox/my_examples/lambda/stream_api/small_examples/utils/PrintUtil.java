package sandbox.my_examples.lambda.stream_api.small_examples.utils;

import sandbox.my_examples.lambda.stream_api.small_examples.example1.EmailAddress;
import sandbox.my_examples.lambda.stream_api.small_examples.example1.Reader;
import sandbox.my_examples.lambda.stream_api.small_examples.example2_grouping.Worker;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class PrintUtil {

    public static void printGroupingByPositionResult(Map<String, List<Worker>> groupingByPosition) {
        printIndent();
        String message = groupingByPosition.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue()
                        .stream()
                        .map(Worker::getName)
                        .collect(joining(",")))
                .collect(joining(",\n"));
        System.out.println("groupingByPosition= \n" + message);
    }

    public static void printCountingByPosition(Map<String, Long> countingByPosition) {
        printIndent();
        String message = countingByPosition.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue())
                .collect(joining(",\n"));
        System.out.println("countingByPosition= \n" + message);
    }

    public static void printGroupingByPositionWithSet(Map<String, Set<Worker>> groupingByPositionWithSet) {
        printIndent();
        String message = groupingByPositionWithSet.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue())
                .collect(joining(",\n"));
        System.out.println("groupingByPositionWithSet= \n" + message);
    }

    public static void printNameGroupingByPosition(Map<String, Set<String>> nameGroupingByPosition) {
        printIndent();
        String message = nameGroupingByPosition.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue())
                .collect(joining(",\n"));
        System.out.println("countingByPosition= \n" + message);
    }

    public static void printGroupingByPositionAndEvSalary(Map<String, Double> groupingByPositionAndEvSalary) {
        printIndent();
        String message = groupingByPositionAndEvSalary.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue())
                .collect(joining(",\n"));
        System.out.println("countingByPosition= \n" + message);
    }

    public static void printGroupingByPositionAndNameIsString(Map<String, String> groupingByPositionAndNameIsString) {
        printIndent();
        String message = groupingByPositionAndNameIsString.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue())
                .collect(joining("\n"));
        System.out.println("countingByPosition= \n" + message);
    }

    public static void printGroupingByPositionAndAge(Map<String, Map<Integer, List<Worker>>> groupingByPositionAndAge) {
        printIndent();
        String message = groupingByPositionAndAge.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue())
                .collect(joining("\n"));
        System.out.println("countingByPosition= \n" + message);
    }

    public static void addressesMap(Map<String, List<EmailAddress>> addressesMap) {
        printIndent();
        String messageAddresses = addressesMap.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + ", value=" + pair.getValue()
                        .stream()
                        .map(EmailAddress::getEmail)
                        .collect(Collectors.joining(",")))
                .collect(joining(","));

        System.out.println("group messageAddresses= " + messageAddresses);
    }

    public static void readersMap(Map<String, List<Reader>> readersMap) {
        printIndent();
        String messageReaders = readersMap.entrySet().stream()
                .map(pair -> "key=" + pair.getKey() + " value=" + pair.getValue()
                        .stream()
                        .map(Reader::getFio)
                        .collect(joining(",")))
                .collect(joining(""));

        System.out.println("group messageReaders= " + messageReaders);
    }

    private static void printIndent() {
        System.out.println("=======================");
    }


}
