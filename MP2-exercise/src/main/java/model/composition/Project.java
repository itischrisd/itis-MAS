package model.composition;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Project {

    private final Set<Task> tasks = new HashSet<>();
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    public void addTask(Task taskToAdd) {
        if (taskToAdd == null) {
            throw new IllegalArgumentException("Cannot add a null task");
        }
        if (Task.getExtent().contains(taskToAdd)) {
            throw new IllegalArgumentException("Task already exists in another project");
        }
        if (taskToAdd.getOwner() != this) {
            throw new IllegalArgumentException("Task must have this project as owner");
        }
        if (tasks.contains(taskToAdd)) {
            return;
        }
        tasks.add(taskToAdd);
    }

    public void removeTask(Task taskToRemove) {
        if (taskToRemove == null) {
            throw new IllegalArgumentException("Cannot remove a null task");
        }
        if (!tasks.contains(taskToRemove)) {
            throw new IllegalArgumentException("Task does not belong to this project");
        }
        tasks.remove(taskToRemove);
        Task.removeFromExtent(taskToRemove);
    }
}
