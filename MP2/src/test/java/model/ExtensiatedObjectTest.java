package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExtensiatedObjectTest {

    private static void writeExtentsToBin(String fileName) {
        try (var fileOut = new FileOutputStream(fileName); var out = new ObjectOutputStream(fileOut)) {
            ExtensiatedObject.writeExtents(out);
            out.writeObject(Product.getTaxRate());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error writing extents to file: " + e.getMessage());
        }
    }

    private static void readExtentsFromBin(String fileName) {
        try (var fileIn = new FileInputStream(fileName); var in = new ObjectInputStream(fileIn)) {
            ExtensiatedObject.readExtents(in);
            Product.setTaxRate((double) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Error reading extents from file: " + e.getMessage());
        }
    }

    @BeforeEach
    void setUp() {
        ExtensiatedObject.clearExtents();
        Product.setTaxRate(0.23);
        new Product("Superglue", new BigDecimal("5.00"), "Strong adhesive");
        new Product("Distilled water", new BigDecimal("1.00"));
        new Tool("Hammer", new BigDecimal("10.00"), "Must have for every toolbox", "Metal");
        new Tool("Screwdriver", new BigDecimal("5.00"), "Metal");
        new BuildingMaterial("Concrete block", new BigDecimal("50.00"), "Basic building material, price per cubic meter", new Dimensions(10, 10, 5));
        new BuildingMaterial("Brick", new BigDecimal("30.00"), "Basic building material, price per cubic meter", new Dimensions(5, 5, 2));
        new BuildingMaterial("Wallpaper", new BigDecimal("20.00"), new Dimensions(2, 2, 0));
    }

    @Test
    void canShowAllExtentsSuccessfully() {
        try {
            ExtensiatedObject.showExtent(Product.class);
            ExtensiatedObject.showExtent(Tool.class);
            ExtensiatedObject.showExtent(BuildingMaterial.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Error showing extents: " + e.getMessage());
        }
    }

    @Test
    void canWriteAllExtentsSuccessfully() {
        String fileName = "extents" + System.currentTimeMillis() + ".bin";
        writeExtentsToBin(fileName);

        assertTrue(new File(fileName).exists());
    }

    @Test
    void canReadAllExtentsSuccessfully() {
        String fileName = "extents" + System.currentTimeMillis() + ".bin";
        writeExtentsToBin(fileName);

        ExtensiatedObject.clearExtents();
        Product.setTaxRate(0.0);

        readExtentsFromBin(fileName);

        try {
            ExtensiatedObject.showExtent(Product.class);
            ExtensiatedObject.showExtent(Tool.class);
            ExtensiatedObject.showExtent(BuildingMaterial.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Error showing extents: " + e.getMessage());
        }

        System.out.println("Tax rate: " + Product.getTaxRate());
    }

    @Test
    void canGetExtentSuccessfully() {
        Iterable<Product> products = null;
        Iterable<Tool> tools = null;
        Iterable<BuildingMaterial> buildingMaterials = null;

        try {
            products = ExtensiatedObject.getExtent(Product.class);
            tools = ExtensiatedObject.getExtent(Tool.class);
            buildingMaterials = ExtensiatedObject.getExtent(BuildingMaterial.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Error getting extent: " + e.getMessage());
        }

        for (Product product : products) {
            System.out.println(product);
        }

        for (Tool tool : tools) {
            System.out.println(tool);
        }

        for (BuildingMaterial buildingMaterial : buildingMaterials) {
            System.out.println(buildingMaterial);
        }
    }

    @Test
    void cannotGetExtentWithNonExistingClass() {
        ExtensiatedObject.clearExtents();
        assertThrows(ClassNotFoundException.class, () -> ExtensiatedObject.getExtent(Product.class));
    }

    @Test
    void cannotShowExtentWithNonExistingClass() {
        ExtensiatedObject.clearExtents();
        assertThrows(ClassNotFoundException.class, () -> ExtensiatedObject.showExtent(Product.class));
    }

    @Test
    void canAddObjectOfSubclassSuccessfully() {
        ExtensiatedObject.clearExtents();
        new Tool("Hammer", new BigDecimal("10.00"), "Must have for every toolbox", "Metal");
        new BuildingMaterial("Concrete block", new BigDecimal("50.00"), "Basic building material, price per cubic meter", new Dimensions(10, 10, 5));

        try {
            ExtensiatedObject.showExtent(Product.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Error showing extent: " + e.getMessage());
        }
    }

    @Test
    void canRemoveObjectSuccessfully() {
        ExtensiatedObject.clearExtents();
        Product product = new Product("Superglue", new BigDecimal("5.00"), "Strong adhesive");
        ExtensiatedObject.removeObject(product);
        assertThrows(ClassNotFoundException.class, () -> ExtensiatedObject.showExtent(Product.class));
    }

    @Test
    void canRemoveObjectOfSubclassSuccessfully() {
        ExtensiatedObject.clearExtents();
        Tool tool = new Tool("Hammer", new BigDecimal("10.00"), "Must have for every toolbox", "Metal");
        ExtensiatedObject.removeObject(tool);
        assertThrows(ClassNotFoundException.class, () -> ExtensiatedObject.showExtent(Tool.class));
    }

    @Test
    void cannotRemoveNullObject() {
        ExtensiatedObject.clearExtents();
        assertThrows(IllegalArgumentException.class, () -> ExtensiatedObject.removeObject(null));
    }

    @Test
    void cannotRemoveObjectNotPresentInExtent() {
        ExtensiatedObject.clearExtents();
        Product product = new Product("Superglue", new BigDecimal("5.00"), "Strong adhesive");
        ExtensiatedObject.removeObject(product);
        assertThrows(IllegalArgumentException.class, () -> ExtensiatedObject.removeObject(product));
    }
}