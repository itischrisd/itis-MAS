package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SupplierTest {

    private Supplier supplier;

    @BeforeEach
    void setUp() {
        supplier = new Supplier("Supplier", 1234567890);
    }

    @Test
    void canGetName() {
        assertEquals("Supplier", supplier.getName());
    }

    @Test
    void canSetName() {
        supplier.setName("New name");
        assertEquals("New name", supplier.getName());
    }

    @Test
    void cannotSetNullName() {
        assertThrows(IllegalArgumentException.class, () -> supplier.setName(null));
    }

    @Test
    void cannotSetBlankName() {
        assertThrows(IllegalArgumentException.class, () -> supplier.setName(" "));
    }

    @Test
    void canGetNIP() {
        assertEquals(1234567890, supplier.getNIP());
    }

    @Test
    void canSetNIP() {
        supplier.setNIP(9876543210L);
        assertEquals(9876543210L, supplier.getNIP());
    }

    @Test
    void cannotSetTooShortNIP() {
        assertThrows(IllegalArgumentException.class, () -> supplier.setNIP(123456789L));
    }

    @Test
    void cannotSetTooLongNIP() {
        assertThrows(IllegalArgumentException.class, () -> supplier.setNIP(12345678901L));
    }
}
