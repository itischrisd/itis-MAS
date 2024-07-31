package model;

import model.withAttribute.Employee;
import model.withAttribute.Partipaction;
import model.withAttribute.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class WithAttributeTest {

    Employee e1;
    Employee e2;
    Project p1;
    Project p2;

    @BeforeEach
    void setup() {
        e1 = new Employee("Kowalski");
        e2 = new Employee("Nowak");
        p1 = new Project("Project 1");
        p2 = new Project("Project 2");
    }

    @Test
    void createSuccessfully() {
        assertThrows(IllegalArgumentException.class, () -> new Partipaction(null, p1, LocalDate.now()), "you should not allow to create a association object with null employee");
        assertThrows(IllegalArgumentException.class, () -> new Partipaction(e1, null, LocalDate.now()), "you should not allow to create a association object with null project");


        Partipaction part1 = new Partipaction(e1, p1, LocalDate.now());

        assertEquals(e1, part1.getEmployee());
        assertEquals(p1, part1.getProject());
        assertTrue(e1.getPartipactions().contains(part1));
        assertTrue(p1.getPartipactions().contains(part1));

        assertThrows(IllegalArgumentException.class, () -> p2.addParticipation(part1));

        assertThrows(IllegalArgumentException.class, () -> e2.addParticipation(part1));

        assertThrows(IllegalArgumentException.class, () -> new Partipaction(e1, p1, LocalDate.now()));

        e1.removeParticipation(part1);
        assertNull(part1.getEmployee());
        assertNull(part1.getProject());
        assertFalse(e1.getPartipactions().contains(part1));
        assertFalse(p1.getPartipactions().contains(part1));
    }
}
