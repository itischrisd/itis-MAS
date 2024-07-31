package model.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FullTimeEmployee extends Employee {

    private BigDecimal monthlySalary;

    public FullTimeEmployee(String firstName, String lastName, String PESEL, String position, LocalDate hireDate, String phoneNumber, Boolean isEligibleForGlassesRefund, BigDecimal monthlySalary) {
        super(firstName, lastName, PESEL, position, hireDate, phoneNumber, isEligibleForGlassesRefund);
        setMonthlySalary(monthlySalary);
    }


    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        if (monthlySalary == null || monthlySalary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monthly salary must be greater than 0");
        }
        this.monthlySalary = monthlySalary;
    }
}
