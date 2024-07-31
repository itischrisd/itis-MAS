package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferTest {

    private Offer offer;

    @BeforeEach
    void setUp() {
        Supplier supplier = new Supplier("Supplier", 1234567890);
        Product product = new Product("Product", new BigDecimal(100));
        offer = new Offer(new BigDecimal(90), 10, supplier, product);
    }

    @Test
    void canGetPrice() {
        assertEquals(new BigDecimal(90), offer.getPrice());
    }
}
