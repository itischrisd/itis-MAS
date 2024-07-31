package model;

import java.util.*;

public class Section extends ExtensiatedObject {

    private static final Set<Alley> allAlleys = new HashSet<>();
    private final List<Alley> alleys = new ArrayList<>();
    private final Map<String, Employee> employees = new HashMap<>();
    private String name;

    public Section(String name) {
        super();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Map<String, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    public Employee getEmployeeByCardNumber(String cardNumber) {
        return employees.get(cardNumber);
    }

    public List<Alley> getAlleys() {
        return Collections.unmodifiableList(alleys);
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Cannot add null employee");
        }
        if (!employees.containsKey(employee.getCardNumber())) {
            employees.put(employee.getCardNumber(), employee);
            employee.addSection(this);
        }
    }

    public void removeEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Cannot remove null employee");
        }
        if (employees.containsKey(employee.getCardNumber())) {
            employees.remove(employee.getCardNumber());
            employee.removeSection(this);
        }
    }

    public void addAlley(Alley alley) {
        if (alley == null) {
            throw new IllegalArgumentException("Cannot add null alley");
        }
        if (alley.getSection() == this && alleys.contains(alley)) {
            return;
        }
        if (allAlleys.contains(alley)) {
            throw new IllegalArgumentException("Alley is already in a section");
        }
        if (alley.getSection() != this) {
            throw new IllegalArgumentException("Alley must have this section as owner");
        }
        alleys.add(alley);
        allAlleys.add(alley);
    }

    public void removeAlley(Alley alley) {
        if (alley == null) {
            throw new IllegalArgumentException("Cannot remove null alley");
        }
        if (alleys.contains(alley)) {
            allAlleys.remove(alley);
            alleys.remove(alley);
            alley.removeAlley();
        }
    }
}
