package model.withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Project {
    private String name;

    private final Set<Partipaction> partipactions = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Partipaction> getPartipactions() {
        return new HashSet<>(partipactions);
    }

    public void addParticipation(Partipaction p) {
        if (p.getProject() == this) {
            partipactions.add(p);
        } else {
            throw new IllegalArgumentException("Project is not the same as the one in the participation");
        }
    }

    public void removeParticipation(Partipaction p) {
        if (p == null) {
            throw new IllegalArgumentException("Participation cannot be null");
        }
        if (partipactions.contains(p)) {
            partipactions.remove(p);
            p.getEmployee().removeParticipation(p);
            p.removeParticipation();
        }
    }
}
