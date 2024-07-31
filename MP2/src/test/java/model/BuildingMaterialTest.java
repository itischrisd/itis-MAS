package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BuildingMaterialTest {

    private BuildingMaterial buildingMaterial;

    @BeforeEach
    void setUp() {
        buildingMaterial = new BuildingMaterial("Concrete block", new BigDecimal("50.00"), "Basic building material, price per cubic meter", new Dimensions(10, 10, 5));
    }

    @Test
    void canCreateSuccessfullyWithDescription() {
        assertEquals("Concrete block", buildingMaterial.getName());
        assertEquals(new BigDecimal("50.00"), buildingMaterial.getNetPrice());
        assertEquals(new Dimensions(10, 10, 5), buildingMaterial.getDimensions());
    }

    @Test
    void canCreateSuccessfullyWithoutDescription() {
        buildingMaterial = new BuildingMaterial("Concrete block", new BigDecimal("50.00"), new Dimensions(10, 10, 5));

        assertEquals("Concrete block", buildingMaterial.getName());
        assertEquals(new BigDecimal("50.00"), buildingMaterial.getNetPrice());
        assertEquals(new Dimensions(10, 10, 5), buildingMaterial.getDimensions());
    }

    @Test
    void canGetDimensionsSuccessfully() {
        assertEquals(new Dimensions(10, 10, 5), buildingMaterial.getDimensions());
    }

    @Test
    void canSetDimensionsSuccessfully() {
        buildingMaterial.setDimensions(new Dimensions(10, 10, 10));

        assertEquals(new Dimensions(10, 10, 10), buildingMaterial.getDimensions());
    }

    @Test
    void cannotSetDimensionsWithNull() {
        assertThrows(IllegalArgumentException.class, () -> buildingMaterial.setDimensions(null));
    }

    @Test
    void cannotSetDimensionsWithNegativeLength() {
        assertThrows(IllegalArgumentException.class, () -> buildingMaterial.setDimensions(new Dimensions(-10, 10, 10)));
    }

    @Test
    void cannotSetDimensionsWithNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> buildingMaterial.setDimensions(new Dimensions(10, -10, 10)));
    }

    @Test
    void cannotSetDimensionsWithNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> buildingMaterial.setDimensions(new Dimensions(10, 10, -10)));
    }

    @Test
    void cannotGetReferenceToDimensions() {
        Dimensions dimensions = buildingMaterial.getDimensions();

        assertNotSame(dimensions, buildingMaterial.getDimensions());
    }
}