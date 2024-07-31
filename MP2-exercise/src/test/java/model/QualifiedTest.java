package model;

import model.qualified.Company;
import model.qualified.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QualifiedTest {

    Company c1;
    Department d1;

    @BeforeEach
    void setup() {
        c1 = new Company("ACME");
        d1 = new Department("Sales");
    }

    @Test
    void createTest() {
        c1.addDepartment(d1);
        assertSame(c1.getDepartments().get(d1.getName()), d1);
        assertSame(d1.getCompany(), c1);

        assertEquals(d1, c1.getDepartmentByName(d1.getName()));
    }


    @Test
    void createTest2() {
        d1.setCompany(c1);
        assertSame(c1.getDepartments().get(d1.getName()), d1);
        assertSame(d1.getCompany(), c1);

        assertEquals(d1, c1.getDepartmentByName(d1.getName()));
    }

    @Test
    void removeTest() {
        c1.addDepartment(d1);
        assertSame(c1.getDepartments().get(d1.getName()), d1);
        assertSame(d1.getCompany(), c1);

        c1.removeDepartment(d1);
        assertNull(d1.getCompany());
        assertFalse(c1.getDepartments().containsKey(d1.getName()));
    }

    @Test
    void removeTest2() {
        c1.addDepartment(d1);
        assertSame(c1.getDepartments().get(d1.getName()), d1);
        assertSame(d1.getCompany(), c1);

        d1.setCompany(null);
        assertNull(d1.getCompany());
        assertFalse(c1.getDepartments().containsKey(d1.getName()));
    }
}
