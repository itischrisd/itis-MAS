package model.composition;

import java.util.HashSet;
import java.util.Set;

public class Task {

    private static final Set<Task> extent = new HashSet<>();
    private final String description;
    private Project owner;

    public Task(String description, Project owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Task must have an owner");
        }
        this.description = description;
        this.owner = owner;
        this.owner.addTask(this);
        extent.add(this);
    }

    public static Set<Task> getExtent() {
        return new HashSet<>(extent);
    }

    public static void removeFromExtent(Task task) {
        if (task == null || !extent.contains(task)) {
            throw new IllegalArgumentException("Task does not exist in the extent or null");
        }
        task.owner = null;
        extent.remove(task);
    }

    public static void resetExtent() {
        extent.clear();
    }

    public Project getOwner() {
        return owner;
    }
}
