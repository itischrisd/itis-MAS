package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AssociationWithAttributeTest {

    private Supplier supplier;
    private Product product;
    private Offer offer;

    @BeforeEach
    void setUp() {
        ExtensiatedObject.clearExtents();
        supplier = new Supplier("Supplier", 1234567890);
        product = new Product("Product", new BigDecimal(100));
        offer = new Offer(new BigDecimal(90), 10, supplier, product);
    }

    @Test
    void canCreateByOfferConstructorSuccessfully() {
        assertTrue(supplier.getOffers().contains(offer));
        assertTrue(product.getOffers().contains(offer));
        assertEquals(supplier, offer.getSupplier());
        assertEquals(product, offer.getProduct());
    }

    @Test
    void cannotCreateOfferWithNullSupplier() {
        assertThrows(IllegalArgumentException.class, () -> new Offer(new BigDecimal(90), 10, null, product));
    }

    @Test
    void cannotCreateOfferWithNullProduct() {
        assertThrows(IllegalArgumentException.class, () -> new Offer(new BigDecimal(90), 10, supplier, null));
    }

    @Test
    void cannotCreateOfferWithNullPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Offer(null, 10, supplier, product));
    }

    @Test
    void cannotCreateOfferWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Offer(new BigDecimal(-1), 10, supplier, product));
    }

    @Test
    void cannotCreateOfferWithZeroPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Offer(BigDecimal.ZERO, 10, supplier, product));
    }

    @Test
    void cannotCreateOfferWithNegativeMinimumQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new Offer(new BigDecimal(90), -1, supplier, product));
    }

    @Test
    void canCreateOfferWithZeroMinimumQuantity() {
        offer.removeOffer();
        Offer offer = new Offer(new BigDecimal(90), 0, supplier, product);
        assertEquals(0, offer.getMinimumQuantity());
    }

    @Test
    void cannotAddNullOfferToProduct() {
        assertThrows(IllegalArgumentException.class, () -> product.addOffer(null));
    }

    @Test
    void cannotAddOfferToProductThatDoesNotBelongToIt() {
        Product otherProduct = new Product("Other product", new BigDecimal(100));
        assertThrows(IllegalArgumentException.class, () -> otherProduct.addOffer(offer));
    }

    @Test
    void canAddOfferToProduct() {
        product.addOffer(offer);
        assertTrue(supplier.getOffers().contains(offer));
        assertTrue(product.getOffers().contains(offer));
        assertEquals(supplier, offer.getSupplier());
        assertEquals(product, offer.getProduct());
    }

    @Test
    void cannotRemoveNullOfferFromProduct() {
        assertThrows(IllegalArgumentException.class, () -> product.removeOffer(null));
    }

    @Test
    void canRemoveOfferFromProduct() {
        product.removeOffer(offer);
        assertFalse(supplier.getOffers().contains(offer));
        assertFalse(product.getOffers().contains(offer));
        assertNull(offer.getSupplier());
        assertNull(offer.getProduct());
    }

    @Test
    void canAddOfferToSupplier() {
        supplier.addOffer(offer);
        assertTrue(supplier.getOffers().contains(offer));
        assertTrue(product.getOffers().contains(offer));
        assertEquals(supplier, offer.getSupplier());
        assertEquals(product, offer.getProduct());
    }

    @Test
    void cannotAddNullOfferToSupplier() {
        assertThrows(IllegalArgumentException.class, () -> supplier.addOffer(null));
    }

    @Test
    void cannotAddOfferToSupplierThatDoesNotBelongToIt() {
        Offer otherOffer = new Offer(new BigDecimal(90), 10, new Supplier("Other supplier", 1234567890), product);
        assertThrows(IllegalArgumentException.class, () -> supplier.addOffer(otherOffer));
    }

    @Test
    void canRemoveOfferFromSupplier() {
        supplier.removeOffer(offer);
        assertFalse(supplier.getOffers().contains(offer));
        assertFalse(product.getOffers().contains(offer));
        assertNull(offer.getSupplier());
        assertNull(offer.getProduct());
    }

    @Test
    void cannotRemoveNullOfferFromSupplier() {
        assertThrows(IllegalArgumentException.class, () -> supplier.removeOffer(null));
    }

    @Test
    void removingOfferNotFromSupplierDoesNotAffectSupplier() {
        Offer otherOffer = new Offer(new BigDecimal(90), 10, new Supplier("Other supplier", 1234567890), product);
        Set<Offer> offers = supplier.getOffers();
        supplier.removeOffer(otherOffer);
        assertEquals(offers, supplier.getOffers());
    }

    @Test
    void removingOfferNotFromProductDoesNotAffectProduct() {
        Offer otherOffer = new Offer(new BigDecimal(90), 10, supplier, new Product("Other product", new BigDecimal(100)));
        Set<Offer> offers = product.getOffers();
        product.removeOffer(otherOffer);
        assertEquals(offers, product.getOffers());
    }

    @Test
    void cannotCreateOfferForExistingProductSupplierPair() {
        assertThrows(IllegalArgumentException.class, () -> new Offer(new BigDecimal(90), 10, supplier, product));
    }

    @Test
    void canRemoveOffer() throws ClassNotFoundException {
        offer.removeOffer();
        assertFalse(supplier.getOffers().contains(offer));
        assertFalse(product.getOffers().contains(offer));
        assertNull(offer.getSupplier());
        assertNull(offer.getProduct());
        new Offer(new BigDecimal(90), 10, supplier, product);
        assertFalse(ExtensiatedObject.getExtent(Offer.class).contains(offer));
    }

    @Test
    void canGetOfferFromProduct() {
        assertTrue(product.getOffers().contains(offer));
    }

    @Test
    void canGetOfferFromSupplier() {
        assertTrue(supplier.getOffers().contains(offer));
    }
}
