package ru.moskalev.sandbox.courses.stepic.java_functional_programming_OLD_2017.four_functional_data_processin.eleven;


import java.util.Arrays;
import java.util.List;

/*4.11 The ﻿general number of employees
You have two classes: Employee (name: String, salary: Long) and Department (name: String, code: String,
employees: List<Employee>). Both classes have getters for all fields with the corresponding names (getName(),
getSalary(), getEmployees() and so on).

Write a method using Stream API that calculates the general number of employees with salary >= threshold for all
 departments whose code starts with string "111-" (without ""). Perhaps, flatMap method can help you to implement it.

Classes Employee and Department will be available during testing. You can define your own classes for local testing.

Important. Use the given template for your method. Pay attention to type of an argument and the returned value.
Please, use only Stream API, no cycles.

Examples: there are 2 departments (in JSON notation) below and ﻿threshold = 20 000. The result is 1 (because there is
 only one suitable employee).

[
  {
    "name": "dep-1",
    "code": "111-1",
    "employees": [
      {
        "name": "William",
        "salary": 20000
      },
      {
        "name": "Sophia",
        "salary": 10000
      }
    ]
  },
  {
    "name": "dep-2",
    "code": "222-1",
    "employees": [
      {
        "name": "John",
        "salary": 50000
      }
    ]
  }
]﻿﻿*/
public class TheGeneralNumberOfEmployees {

    /**
     * Calculates the number of employees with salary >= threshold (only for 111- departments)
     *
     * @param departments are list of departments
     * @param threshold   is lower edge of salary
     * @return the number of employees
     */
    public static long calcNumberOfEmployees(List<Department> departments, long threshold) {
        String codeStartWith = "111-";

        return departments
                .stream()
                .filter(department -> department.getCode()
                        .startsWith(codeStartWith))
                .flatMap(department -> department.getEmployees().stream())
                .filter(employee -> employee.getSalary() >= threshold)
                .count();
    }

    public static void main(String[] args) {
        Employee employee1 = new Employee("Koliy", 50000);
        Employee employee2 = new Employee("Nik", 20000);
        Department dep1 = new Department("dep1", "111-1");
        dep1.addEmployee(employee1);
        dep1.addEmployee(employee2);

        Department dep2 = new Department("dep2", "222-1");
        dep2.addEmployee(employee1);
        dep2.addEmployee(employee2);

        long result = calcNumberOfEmployees(Arrays.asList(dep1, dep2), 50000);
        System.out.println(result); //1

    }
}
