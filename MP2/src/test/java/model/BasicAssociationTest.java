package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAssociationTest {

    private Alley alley;
    private Product product;

    @BeforeEach
    void setUp() {
        ExtensiatedObject.clearExtents();
        Section section = new Section("Section");
        alley = Alley.createAlley(1, section);
        product = new Product("Product", new BigDecimal(100));
    }

    @Test
    void canSetAlleyInProductWhenCurrentAlleyIsNull() {
        product.setAlley(alley);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
    }

    @Test
    void canSetAlleyInProductWhenNewAlleyIsCurrentAlley() {
        product.setAlley(alley);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        product.setAlley(alley);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
    }

    @Test
    void canSetAlleyInProductWhenNewAndCurrentAlleysAreNull() {
        product.setAlley(null);
        assertSame(null, product.getAlley());
        assertEquals(0, alley.getProducts().size());
    }

    @Test
    void canSetAlleyInProductWhenNewAlleyIsDifferentFromCurrentAlley() {
        product.setAlley(alley);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        Alley newAlley = Alley.createAlley(2, new Section("Section 2"));
        product.setAlley(newAlley);
        assertSame(newAlley, product.getAlley());
        assertSame(product, newAlley.getProducts().iterator().next());
        assertEquals(0, alley.getProducts().size());
    }

    @Test
    void canSetAlleyToNullInProductWhenCurrentAlleyIsNotNull() {
        product.setAlley(alley);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        product.setAlley(null);
        assertSame(null, product.getAlley());
        assertEquals(0, alley.getProducts().size());
    }

    @Test
    void cannotAddNullProductToAlley() {
        assertThrows(IllegalArgumentException.class, () -> alley.addProduct(null));
    }

    @Test
    void cannotRemoveNullProductFromAlley() {
        assertThrows(IllegalArgumentException.class, () -> alley.removeProduct(null));
    }

    @Test
    void canAddNewProductToAlley() {
        alley.addProduct(product);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
    }

    @Test
    void canAddProductToAlleyWhenProductIsAlreadyInAlley() {
        alley.addProduct(product);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        alley.addProduct(product);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
    }

    @Test
    void canRemoveProductFromAlley() {
        alley.addProduct(product);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        alley.removeProduct(product);
        assertSame(null, product.getAlley());
        assertEquals(0, alley.getProducts().size());
    }

    @Test
    void canRemoveProductFromAlleyWhenProductIsNotInAlley() {
        alley.addProduct(product);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        Product newProduct = new Product("New Product", new BigDecimal(200));
        alley.removeProduct(newProduct);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        assertEquals(1, alley.getProducts().size());
    }

    @Test
    void productsAreRemovedFromAlleyWhenAlleyIsRemoved() {
        alley.addProduct(product);
        assertSame(alley, product.getAlley());
        assertSame(product, alley.getProducts().iterator().next());
        alley.removeAlley();
        assertSame(null, product.getAlley());
        assertEquals(0, alley.getProducts().size());
    }

    @Test
    void canGetProductsFromAlley() {
        alley.addProduct(product);
        assertSame(product, alley.getProducts().iterator().next());
    }

    @Test
    void canGetAlleyFromProduct() {
        product.setAlley(alley);
        assertSame(alley, product.getAlley());
    }
}
