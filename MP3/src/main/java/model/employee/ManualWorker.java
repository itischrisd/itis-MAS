package model.employee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ManualWorker extends Worker {

    private final Set<String> harmfulFactors = new HashSet<>();

    private ManualWorker(Employee employee) {
        super(employee);
    }

    public static ManualWorker createManualWorker(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (Employee.getExtent().contains(employee)) {
            throw new IllegalArgumentException("Employee already exists");
        }
        return new ManualWorker(employee);
    }

    public Set<String> getHarmfulFactors() {
        return Collections.unmodifiableSet(harmfulFactors);
    }

    public void addHarmfulFactor(String factor) {
        harmfulFactors.add(factor);
    }

    public void removeHarmfulFactor(String factor) {
        harmfulFactors.remove(factor);
    }
}
