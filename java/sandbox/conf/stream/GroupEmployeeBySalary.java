package sandbox.conf.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Найти отделы, где суммарная зарплата работников более миллиона, отсортировать по убыванию суммарной зарплаты
 */
public class GroupEmployeeBySalary {
    public static void main(String[] args) {

    }

    public Map<Department, Long> groupBySalary(List<Employee> employeeList) {
        Map<Department, Long> map = employeeList
                .stream()
                .collect(Collectors.groupingBy(e -> e.department,
                        Collectors.summingLong(e -> e.salary)));

        return map.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1_000_000)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    class Employee {
        Department department;
        long salary;
    }

    class Department {

    }
}
