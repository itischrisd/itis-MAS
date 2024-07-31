package model.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contractor extends Employee {

    private BigDecimal contractPayment;
    private String descriptionOfContractedWork;
    private LocalDate contractPaymentDate;

    public Contractor(String firstName, String lastName, String PESEL, String position, LocalDate hireDate, String phoneNumber, Boolean isEligibleForGlassesRefund, BigDecimal contractPayment, String descriptionOfContractedWork, LocalDate contractPaymentDate) {
        super(firstName, lastName, PESEL, position, hireDate, phoneNumber, isEligibleForGlassesRefund);
        setContractPayment(contractPayment);
        setDescriptionOfContractedWork(descriptionOfContractedWork);
        setContractPaymentDate(contractPaymentDate);
    }


    public BigDecimal getContractPayment() {
        return contractPayment;
    }

    public void setContractPayment(BigDecimal contractPayment) {
        if (contractPayment == null || contractPayment.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Contract payment must be greater than 0");
        }
        this.contractPayment = contractPayment;
    }

    public String getDescriptionOfContractedWork() {
        return descriptionOfContractedWork;
    }

    public void setDescriptionOfContractedWork(String descriptionOfContractedWork) {
        if (descriptionOfContractedWork == null || descriptionOfContractedWork.isBlank()) {
            throw new IllegalArgumentException("Description of contracted work cannot be null or empty");
        }
        this.descriptionOfContractedWork = descriptionOfContractedWork;
    }

    public LocalDate getContractPaymentDate() {
        return contractPaymentDate;
    }

    public void setContractPaymentDate(LocalDate contractPaymentDate) {
        if (contractPaymentDate == null) {
            throw new IllegalArgumentException("Contract payment date cannot be null");
        }
        if (contractPaymentDate.isBefore(getHireDate())) {
            throw new IllegalArgumentException("Contract payment date cannot be before hire date");
        }
        this.contractPaymentDate = contractPaymentDate;
    }
}
