package homework4;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDirectory {
    private final Map<Integer, Employee> employeeMap;

    public EmployeeDirectory() {
        employeeMap = new HashMap<>();
    }

    public void addEmployee(Employee employee) {
        employeeMap.put(employee.id(), employee);
    }

    public Employee findById(int id) {
        return employeeMap.get(id);
    }

    public List<Employee> findByExperience(int experience) {
        return employeeMap
                .values()
                .stream()
                .filter(e -> e.experience() == experience)
                .collect(Collectors
                        .toList());
    }

    public List<Long> findPhoneNumbersByName(String name) {
        return employeeMap
                .values()
                .stream()
                .filter(e -> e.name()
                        .equalsIgnoreCase(name))
                .map(Employee::phoneNumber)
                .collect(Collectors
                        .toList());
    }

    public void printAll() {
        employeeMap.values().forEach(System.out::println);
    }
}

