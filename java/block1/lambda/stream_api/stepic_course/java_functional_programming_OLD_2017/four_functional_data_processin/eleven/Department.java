package block1.lambda.stream_api.stepic_course.java_functional_programming_OLD_2017.four_functional_data_processin.eleven;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private String code;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee empl) {
        employees.add(empl);
    }
}
