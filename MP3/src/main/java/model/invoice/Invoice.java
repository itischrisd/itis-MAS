package model.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Invoice {

    private static final BigDecimal VAT_RATE = new BigDecimal("0.23");
    private String number;
    private LocalDate issueDate;
    private BigDecimal netAmount;

    protected Invoice(String number, LocalDate issueDate, BigDecimal netAmount) {
        setNumber(number);
        setIssueDate(issueDate);
        setNetAmount(netAmount);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be null or empty");
        }
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        if (issueDate == null) {
            throw new IllegalArgumentException("Issue date cannot be null");
        }
        this.issueDate = issueDate;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        if (netAmount == null || netAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Net amount cannot be null or negative");
        }
        this.netAmount = netAmount;
    }

    public BigDecimal getGrossAmount() {
        return netAmount.add(netAmount.multiply(VAT_RATE));
    }

    public abstract void record();
}
