package model;

import model.employee.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @BeforeAll
    static void setup() {
        Employee.addAvailablePosition("Manager");
    }

    @Test
    void canCreateFullTimeEmployeeWithManualWorker() {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", null, BigDecimal.valueOf(5000));

        assertNotNull(fullTimeEmployee);
        assertEquals(fullTimeEmployee.asManualWorker().getClass(), ManualWorker.class);
    }

    @Test
    void canCreateFullTimeEmployeeWithOfficeWorker() {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", true, BigDecimal.valueOf(5000));

        assertNotNull(fullTimeEmployee);
        assertEquals(fullTimeEmployee.asOfficeWorker().getClass(), OfficeWorker.class);
    }

    @Test
    void canCreateContractorWithManualWorker() {
        Contractor contractor = new Contractor("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", null, BigDecimal.valueOf(5000), "Digging", LocalDate.now());

        assertNotNull(contractor);
        assertEquals(contractor.asManualWorker().getClass(), ManualWorker.class);
    }

    @Test
    void canCreateContractorWithOfficeWorker() {
        Contractor contractor = new Contractor("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", true, BigDecimal.valueOf(5000), "Digging", LocalDate.now());

        assertNotNull(contractor);
        assertEquals(contractor.asOfficeWorker().getClass(), OfficeWorker.class);
    }

    @Test
    void canCreateInternWithManualWorker() {
        Intern intern = new Intern("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", null, LocalDate.now());

        assertNotNull(intern);
        assertEquals(intern.asManualWorker().getClass(), ManualWorker.class);
    }

    @Test
    void canCreateInternWithOfficeWorker() {
        Intern intern = new Intern("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", true, LocalDate.now());

        assertNotNull(intern);
        assertEquals(intern.asOfficeWorker().getClass(), OfficeWorker.class);
    }

    @Test
    void canGetEligibleForGlassesRefund() {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", true, BigDecimal.valueOf(5000));

        assertNotNull(fullTimeEmployee);
        assertTrue(fullTimeEmployee.asOfficeWorker().isEligibleForGlassesRefund());
    }

    @Test
    void canAddHarmfulFactorToManualWorker() {
        Employee employee = new FullTimeEmployee("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", null, BigDecimal.valueOf(5000));

        employee.asManualWorker().addHarmfulFactor("Noise");

        assertTrue(employee.asManualWorker().getHarmfulFactors().contains("Noise"));
    }

    @Test
    void cannotGetManualWorkerFromOfficeWorker() {
        Employee employee = new FullTimeEmployee("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", true, BigDecimal.valueOf(5000));

        assertThrows(IllegalArgumentException.class, employee::asManualWorker);
    }

    @Test
    void cannotGetOfficeWorkerFromManualWorker() {
        Employee employee = new FullTimeEmployee("John", "Doe", "12345678963", "Manager", LocalDate.now(), "123456789", null, BigDecimal.valueOf(5000));

        assertThrows(IllegalArgumentException.class, employee::asOfficeWorker);
    }
}
