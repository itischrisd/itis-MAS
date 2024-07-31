package model;

import model.basic.Department;
import model.basic.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAssociationTest {
    Employee e1;
    Department d1;
    Department d2;

    @BeforeEach
    void setup() {
        e1 = new Employee("Kowalski");
        d1 = new Department("Sales");
        d2 = new Department("HR");
    }


    @Test
    void setDeptTest() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn());
        assertTrue(d1.getEmployees().contains(e1));
    }

    @Test
    void addEmpTest() {
        assertThrows(IllegalArgumentException.class, () -> d1.addEmployee(null));
        d1.addEmployee(e1);
        assertEquals(d1, e1.getWorksIn());
        assertTrue(d1.getEmployees().contains(e1));
    }

    @Test
    void addAndRemoveFromEmp() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn());
        assertTrue(d1.getEmployees().contains(e1));

        e1.setWorksIn(null);
        assertNull(e1.getWorksIn());
        assertFalse(d1.getEmployees().contains(e1));

    }

    @Test
    void addAndRemoveFromDept() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn());
        assertTrue(d1.getEmployees().contains(e1));

        d1.removeEmployee(e1);
        assertNull(e1.getWorksIn());
        assertFalse(d1.getEmployees().contains(e1));

    }

    @Test
    void testReplaceDepartment() {
        e1.setWorksIn(d1);
        assertEquals(d1, e1.getWorksIn());
        assertTrue(d1.getEmployees().contains(e1));

        e1.setWorksIn(d2);

        assertFalse(d1.getEmployees().contains(e1));

        assertEquals(d2, e1.getWorksIn());
        assertTrue(d2.getEmployees().contains(e1));
    }
}