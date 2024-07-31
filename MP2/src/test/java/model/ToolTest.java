package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToolTest {

    private Tool tool;

    @BeforeEach
    void setUp() {
        tool = new Tool("Hammer", new BigDecimal("10.00"), "Must have for every toolbox", "Metal");
        tool.addManufacturingMaterial("Wood");
    }

    @Test
    void canCreateSuccessfullyWithDescription() {
        List<String> materials = tool.getManufacturingMaterials();

        assertEquals("Hammer", tool.getName());
        assertEquals(new BigDecimal("10.00"), tool.getNetPrice());
        assertEquals("Must have for every toolbox", tool.getDescription());
        assertEquals(2, materials.size());
        assertEquals("Metal", materials.get(0));
        assertEquals("Wood", materials.get(1));
    }

    @Test
    void canCreateSuccessfullyWithoutDescription() {
        tool = new Tool("Hammer", new BigDecimal("10.00"), "Metal");
        tool.addManufacturingMaterial("Wood");

        List<String> materials = tool.getManufacturingMaterials();

        assertEquals("Hammer", tool.getName());
        assertEquals(new BigDecimal("10.00"), tool.getNetPrice());
        assertNull(tool.getDescription());
        assertEquals(2, materials.size());
        assertEquals("Metal", materials.get(0));
        assertEquals("Wood", materials.get(1));
    }

    @Test
    void canGetManufacturingMaterialsSuccessfully() {
        List<String> materials = tool.getManufacturingMaterials();

        assertEquals(2, materials.size());
        assertEquals("Metal", materials.get(0));
        assertEquals("Wood", materials.get(1));
    }

    @Test
    void canAddManufacturingMaterialSuccessfully() {
        tool.addManufacturingMaterial("Plastic");
        List<String> materials = tool.getManufacturingMaterials();

        assertEquals(3, materials.size());
        assertEquals("Metal", materials.get(0));
        assertEquals("Wood", materials.get(1));
        assertEquals("Plastic", materials.get(2));
    }

    @Test
    void removeManufacturingMaterial() {
        tool.removeManufacturingMaterial("Wood");
        List<String> materials = tool.getManufacturingMaterials();

        assertEquals(1, materials.size());
        assertEquals("Metal", materials.getFirst());
    }

    @Test
    void cannotAddNullMaterial() {
        assertThrows(IllegalArgumentException.class, () -> tool.addManufacturingMaterial(null));
    }

    @Test
    void cannotAddEmptyMaterial() {
        assertThrows(IllegalArgumentException.class, () -> tool.addManufacturingMaterial(" "));
    }

    @Test
    void cannotRemoveLastMaterial() {
        tool.removeManufacturingMaterial("Metal");

        assertThrows(IllegalArgumentException.class, () -> tool.removeManufacturingMaterial("Wood"));
    }

    @Test
    void canRemoveMaterialSuccessfully() {
        tool.removeManufacturingMaterial("Wood");

        List<String> materials = tool.getManufacturingMaterials();

        assertEquals(1, materials.size());
        assertEquals("Metal", materials.getFirst());
    }

    @Test
    void cannotRemoveMaterialNotInList() {
        assertThrows(IllegalArgumentException.class, () -> tool.removeManufacturingMaterial("Plastic"));
    }

    @Test
    void cannotAddDuplicateMaterial() {
        assertThrows(IllegalArgumentException.class, () -> tool.addManufacturingMaterial("Metal"));
    }
}