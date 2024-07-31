package model.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomingInvoice extends Invoice {

    private LocalDate paymentDate;

    public IncomingInvoice(String number, LocalDate issueDate, BigDecimal netAmount) {
        super(number, issueDate, netAmount);
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    private void setPaymentDate(LocalDate paymentDate) {
        if (paymentDate == null) {
            throw new IllegalArgumentException("Payment date cannot be null");
        }
        if (paymentDate.isBefore(getIssueDate())) {
            throw new IllegalArgumentException("Payment date cannot be before issue date");
        }
        this.paymentDate = paymentDate;
    }

    @Override
    public void record() {
        if (getNetAmount().compareTo(Balance.getAmount()) > 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        Balance.subtractAmount(getNetAmount());
        setPaymentDate(LocalDate.now());
    }
}
