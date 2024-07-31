package model;

import model.composition.Project;
import model.composition.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositionTest {

    Project p1;
    Project p2;


    @BeforeEach
    void setup() {
        Task.resetExtent();
        p1 = new Project("Project 1");
        p2 = new Project("Project 2");
    }

    @Test
    void createTaskTest() {
        assertThrows(IllegalArgumentException.class, () -> new Task("desc", null));
        Task t1 = new Task("desc", p1);
        assertEquals(p1, t1.getOwner());
        assertTrue(p1.getTasks().contains(t1));
    }

    @Test
    void removeTaskFromProjectTest() {
        Task t1 = new Task("desc", p1);
        assertEquals(p1, t1.getOwner());
        assertTrue(p1.getTasks().contains(t1));
        assertTrue(Task.getExtent().contains(t1));

        p1.removeTask(t1);
        assertNull(t1.getOwner());
        assertFalse(p1.getTasks().contains(t1));

        assertFalse(Task.getExtent().contains(t1));
    }

    @Test
    void switchTaskOwner() {
        Task t1 = new Task("desc", p1);
        assertEquals(p1, t1.getOwner());
        assertTrue(p1.getTasks().contains(t1));

        assertThrows(IllegalArgumentException.class, () -> p2.addTask(t1));
    }
}
