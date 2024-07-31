package model.employee;

import java.time.LocalDate;

public class Intern extends Employee {

    private LocalDate internshipEndDate;

    public Intern(String firstName, String lastName, String PESEL, String position, LocalDate hireDate, String phoneNumber, Boolean isEligibleForGlassesRefund, LocalDate internshipEndDate) {
        super(firstName, lastName, PESEL, position, hireDate, phoneNumber, isEligibleForGlassesRefund);
        setInternshipEndDate(internshipEndDate);
    }

    public LocalDate getInternshipEndDate() {
        return internshipEndDate;
    }

    public void setInternshipEndDate(LocalDate internshipEndDate) {
        if (internshipEndDate == null) {
            throw new IllegalArgumentException("Internship end date cannot be null");
        }
        if (internshipEndDate.isBefore(getInternshipStartDate())) {
            throw new IllegalArgumentException("Internship end date cannot be before internship start date");
        }
        this.internshipEndDate = internshipEndDate;
    }

    public LocalDate getInternshipStartDate() {
        return getHireDate();
    }
}
