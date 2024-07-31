package model;

import model.invoice.Balance;
import model.invoice.IncomingInvoice;
import model.invoice.Invoice;
import model.invoice.OutgoingInvoice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceTest {

    @Test
    void canRecordOutgoingInvoice() {
        Invoice invoice = new OutgoingInvoice("123", LocalDate.now(), LocalDate.now().plusDays(30), BigDecimal.valueOf(1000));
        Balance.setAmount(BigDecimal.valueOf(1000));
        invoice.record();
        assertEquals(Balance.getAmount(), BigDecimal.valueOf(2000));
    }

    @Test
    void canRecordIncomingInvoice() {
        Invoice invoice = new IncomingInvoice("123", LocalDate.now(), BigDecimal.valueOf(1000));
        Balance.setAmount(BigDecimal.valueOf(2000));
        invoice.record();
        assertEquals(Balance.getAmount(), BigDecimal.valueOf(1000));
        assertEquals(((IncomingInvoice) invoice).getPaymentDate(), LocalDate.now());
    }
}
