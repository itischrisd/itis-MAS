package model.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OutgoingInvoice extends Invoice {

    private LocalDate dueDate;

    public OutgoingInvoice(String number, LocalDate issueDate, LocalDate dueDate, BigDecimal netAmount) {
        super(number, issueDate, netAmount);
        setDueDate(dueDate);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }
        if (dueDate.isBefore(getIssueDate())) {
            throw new IllegalArgumentException("Due date cannot be before issue date");
        }
        this.dueDate = dueDate;
    }

    @Override
    public void record() {
        Balance.addAmount(getNetAmount());
    }
}
