package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QualifiedAssociationTest {

    private Section section;
    private Employee employee;

    @BeforeEach
    void setUp() {
        ExtensiatedObject.clearExtents();
        section = new Section("Section");
        employee = new Employee("John", "Doe");
    }

    @Test
    void canAddEmployeeToSection() {
        section.addEmployee(employee);
        assertTrue(section.getEmployees().containsValue(employee));
        assertSame(section, employee.getSections().iterator().next());
    }

    @Test
    void canRemoveEmployeeFromSection() {
        section.addEmployee(employee);
        section.removeEmployee(employee);
        assertFalse(section.getEmployees().containsValue(employee));
        assertTrue(employee.getSections().isEmpty());
    }

    @Test
    void cannotAddNullEmployee() {
        assertThrows(IllegalArgumentException.class, () -> section.addEmployee(null));
    }

    @Test
    void cannotRemoveNullEmployee() {
        assertThrows(IllegalArgumentException.class, () -> section.removeEmployee(null));
    }

    @Test
    void canGetEmployeeByCardNumber() {
        section.addEmployee(employee);
        assertEquals(employee, section.getEmployeeByCardNumber(employee.getCardNumber()));
    }

    @Test
    void canGetAllEmployeesFromSection() {
        Employee employee2 = new Employee("Jane", "Doe");
        section.addEmployee(employee);
        section.addEmployee(employee2);
        assertEquals(2, section.getEmployees().size());
        assertTrue(section.getEmployees().containsValue(employee));
        assertTrue(section.getEmployees().containsValue(employee2));
    }

    @Test
    void canRemoveSectionFromEmployee() {
        section.addEmployee(employee);
        employee.removeSection(section);
        assertFalse(section.getEmployees().containsValue(employee));
        assertTrue(employee.getSections().isEmpty());
    }

    @Test
    void cannotAddNullSection() {
        assertThrows(IllegalArgumentException.class, () -> employee.addSection(null));
    }

    @Test
    void cannotRemoveNullSection() {
        assertThrows(IllegalArgumentException.class, () -> employee.removeSection(null));
    }

    @Test
    void addingEmployeeToSectionTwiceDoesNotDuplicate() {
        section.addEmployee(employee);
        section.addEmployee(employee);
        assertEquals(1, section.getEmployees().size());
    }

    @Test
    void removingEmployeeFromSectionTwiceDoesNotDuplicate() {
        section.addEmployee(employee);
        section.removeEmployee(employee);
        section.removeEmployee(employee);
        assertEquals(0, section.getEmployees().size());
    }

    @Test
    void removingEmployeeFromSectionDoesNotRemoveFromOtherSections() {
        Section section2 = new Section("Section 2");
        section.addEmployee(employee);
        section2.addEmployee(employee);
        section.removeEmployee(employee);
        assertTrue(section2.getEmployees().containsValue(employee));
    }

    @Test
    void removingSectionFromEmployeeDoesNotRemoveFromOtherEmployees() {
        Employee employee2 = new Employee("Jane", "Doe");
        section.addEmployee(employee);
        section.addEmployee(employee2);
        employee.removeSection(section);
        assertTrue(section.getEmployees().containsValue(employee2));
    }

    @Test
    void addingSectionToEmployeeTwiceDoesNotDuplicate() {
        employee.addSection(section);
        employee.addSection(section);
        assertEquals(1, employee.getSections().size());
    }

    @Test
    void removingSectionFromEmployeeTwiceDoesNotDuplicate() {
        employee.addSection(section);
        employee.removeSection(section);
        employee.removeSection(section);
        assertEquals(0, employee.getSections().size());
    }
}
