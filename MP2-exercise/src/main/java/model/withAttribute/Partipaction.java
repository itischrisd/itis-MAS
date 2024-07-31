package model.withAttribute;

import java.time.LocalDate;

public class Partipaction {
    private Employee employee;
    private Project project;

    private LocalDate startAt;

    public Partipaction(Employee employee, Project project, LocalDate startAt) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        if (startAt == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (employee.getPartipactions().stream().anyMatch(e -> e.getProject() == project)) {
            throw new IllegalArgumentException("Employee is already participating in this project");
        }
        this.employee = employee;
        this.project = project;
        this.startAt = startAt;
        employee.addParticipation(this);
        project.addParticipation(this);
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public void removeParticipation() {
        setEmployee(null);
        setProject(null);
    }

    private void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private void setProject(Project project) {
        this.project = project;
    }
}
