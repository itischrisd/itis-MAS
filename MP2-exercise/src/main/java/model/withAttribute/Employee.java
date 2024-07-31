package model.withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Employee {
    private final Set<Partipaction> partipactions = new HashSet<>();
    private String lastName;

    public Employee(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Partipaction> getPartipactions() {
        return new HashSet<>(partipactions);
    }

    public void addParticipation(Partipaction p) {
        if (p.getEmployee() == this) {
            partipactions.add(p);
        } else {
            throw new IllegalArgumentException("Employee is not the same as the one in the participation");
        }
    }

    public void removeParticipation(Partipaction p) {
        if (p == null) {
            throw new IllegalArgumentException("Participation cannot be null");
        }
        if (partipactions.contains(p)) {
            partipactions.remove(p);
            p.getProject().removeParticipation(p);
            p.removeParticipation();
        }
    }
}
