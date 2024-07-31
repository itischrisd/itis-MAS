package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Superglue", new BigDecimal("5.00"), "Strong adhesive");
    }

    @Test
    void canCreateSuccessfullyWithDescription() {
        assertEquals("Superglue", product.getName());
        assertEquals(new BigDecimal("5.00"), product.getNetPrice());
        assertEquals("Strong adhesive", product.getDescription());
    }

    @Test
    void canCreateSuccessfullyWithoutDescription() {
        product = new Product("Superglue", new BigDecimal("5.00"));

        assertEquals("Superglue", product.getName());
        assertEquals(new BigDecimal("5.00"), product.getNetPrice());
        assertNull(product.getDescription());
    }

    @Test
    void canGetNameSuccessfully() {
        assertEquals("Superglue", product.getName());
    }

    @Test
    void canSetNameSuccessfully() {
        product.setName("Super glue");

        assertEquals("Super glue", product.getName());
    }

    @Test
    void cannotSetNameWithNull() {
        assertThrows(IllegalArgumentException.class, () -> product.setName(null));
    }

    @Test
    void cannotSetNameWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> product.setName(" "));
    }

    @Test
    void canGetNetPriceSuccessfully() {
        assertEquals(new BigDecimal("5.00"), product.getNetPrice());
    }

    @Test
    void canSetNetPriceSuccessfully() {
        product.setNetPrice(new BigDecimal("6.00"));

        assertEquals(new BigDecimal("6.00"), product.getNetPrice());
    }

    @Test
    void cannotSetNetPriceWithNull() {
        assertThrows(IllegalArgumentException.class, () -> product.setNetPrice(null));
    }

    @Test
    void cannotSetNetPriceWithZero() {
        assertThrows(IllegalArgumentException.class, () -> product.setNetPrice(BigDecimal.ZERO));
    }

    @Test
    void cannotSetNetPriceWithNegative() {
        assertThrows(IllegalArgumentException.class, () -> product.setNetPrice(new BigDecimal("-1.00")));
    }

    @Test
    void canGetGrossPriceSuccessfully() {
        assertEquals(new BigDecimal("6.15"), product.getGrossPrice());
    }

    @Test
    void canGetDescriptionSuccessfully() {
        assertEquals("Strong adhesive", product.getDescription());
    }

    @Test
    void canSetDescriptionSuccessfully() {
        product.setDescription("Strong adhesive for all materials");

        assertEquals("Strong adhesive for all materials", product.getDescription());
    }

    @Test
    void canSetDescriptionToNull() {
        product.setDescription(null);

        assertNull(product.getDescription());
    }

    @Test
    void cannotSetDescriptionToEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> product.setDescription(" "));
    }

    @Test
    void canGetTaxRateSuccessfully() {
        assertEquals(0.23, Product.getTaxRate());
    }

    @Test
    void canSetTaxRateSuccessfully() {
        Product.setTaxRate(0.24);

        assertEquals(0.24, Product.getTaxRate());

        Product.setTaxRate(0.23);
    }

    @Test
    void cannotSetTaxRateLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> Product.setTaxRate(-0.01));
    }

    @Test
    void canGetNetSumOfAllProductsSuccessfully() throws ClassNotFoundException {
        ExtensiatedObject.clearExtents();
        new Product("Superglue", new BigDecimal("5.00"), "Strong adhesive");
        new Product("Distilled water", new BigDecimal("1.00"));
        new Tool("Hammer", new BigDecimal("10.00"), "Must have for every toolbox", "Metal");
        new Tool("Screwdriver", new BigDecimal("5.00"), "Metal");
        new BuildingMaterial("Concrete block", new BigDecimal("50.00"), "Basic building material, price per cubic meter", new Dimensions(10, 10, 5));
        new BuildingMaterial("Brick", new BigDecimal("30.00"), "Basic building material, price per cubic meter", new Dimensions(5, 5, 2));
        new BuildingMaterial("Wallpaper", new BigDecimal("20.00"), new Dimensions(2, 2, 0));
        assertEquals(new BigDecimal("121.00"), Product.calculateNetValueOfAllProducts());
    }

    @Test
    void cannotGetNetSumOfAllProductsWhenExtentNotFound() {
        ExtensiatedObject.clearExtents();
        assertThrows(ClassNotFoundException.class, Product::calculateNetValueOfAllProducts);
    }

    @Test
    void canSellProductSuccessfully() {
        BigDecimal price = product.sell();
        assertEquals(new BigDecimal("6.15"), price);
    }

    @Test
    void canSellProductWithDiscountSuccessfully() {
        BigDecimal price = product.sell(0.10);
        assertEquals(new BigDecimal("5.53"), price);
    }

    @Test
    void cannotSellProductWithNegativeDiscount() {
        assertThrows(IllegalArgumentException.class, () -> product.sell(-0.10));
    }

    @Test
    void cannotSellProductWithDiscountGreaterThanOne() {
        assertThrows(IllegalArgumentException.class, () -> product.sell(1.10));
    }
}