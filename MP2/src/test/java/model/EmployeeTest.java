package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John", "Doe");
    }

    @Test
    void canGetName() {
        assertEquals("John", employee.getName());
    }

    @Test
    void canGetSurname() {
        assertEquals("Doe", employee.getSurname());
    }

    @Test
    void canGetCardNumber() {
        assertTrue(employee.getCardNumber().startsWith("EMP"));
        assertEquals(8, employee.getCardNumber().length());
    }

    @Test
    void cannoSetNullName() {
        assertThrows(IllegalArgumentException.class, () -> employee.setName(null));
    }

    @Test
    void cannoSetNullSurname() {
        assertThrows(IllegalArgumentException.class, () -> employee.setSurname(null));
    }

    @Test
    void cannotSetBlankName() {
        assertThrows(IllegalArgumentException.class, () -> employee.setName(" "));
    }

    @Test
    void cannotSetBlankSurname() {
        assertThrows(IllegalArgumentException.class, () -> employee.setSurname(" "));
    }
}
