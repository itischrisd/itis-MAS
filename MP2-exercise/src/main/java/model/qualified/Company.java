package model.qualified;

import java.util.HashMap;
import java.util.Map;

public class Company {
    private String name;

    private final Map<String, Department> departments = new HashMap<>();


    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Department> getDepartments() {
        return new HashMap<>(departments);
    }

    public Department getDepartmentByName(String name) {
        return departments.get(name);
    }

    public void addDepartment(Department departmentToAdd) {
        if (departmentToAdd == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        if (!departments.containsKey(departmentToAdd.getName())) {
            departments.put(departmentToAdd.getName(), departmentToAdd);
            departmentToAdd.setCompany(this);
        }
    }

    public void removeDepartment(Department departmentToRemove) {
        if (departmentToRemove == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        if (departments.containsKey(departmentToRemove.getName())) {
            departments.remove(departmentToRemove.getName());
            departmentToRemove.setCompany(null);
        }
    }
}
