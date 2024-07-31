package model.basic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Department {
    private final Set<Employee> employees = new HashSet<>();
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return Collections.unmodifiableSet(employees);
    }

    public void addEmployee(Employee newEmployee) {
        if (newEmployee == null) {
            throw new IllegalArgumentException("Cannot add null employee");
        }
        if (employees.contains(newEmployee)) {
            return;
        }
        this.employees.add(newEmployee);
        newEmployee.setWorksIn(this);
    }

    public void removeEmployee(Employee employeeToRemove) {
        if (employees.contains(employeeToRemove)) {
            employees.remove(employeeToRemove);
            employeeToRemove.setWorksIn(null);
        }
    }
}

