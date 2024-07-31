package model;

import model.product.BuildingMaterial;
import model.product.HouseholdEquipment;
import model.product.Product;
import model.product.Tool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @BeforeAll
    static void setup() {
        Product.addAvailableMaterial("material");
    }

    @Test
    void cannotCreateProductWithoutSubclass() {
        assertThrows(IllegalArgumentException.class, () -> new Product("name", "description", BigDecimal.valueOf(100), null, "material", null, null, null, null, null));
    }

    @Test
    void canCreateBuildingMaterial() {
        Product product = new Product("name", "description", BigDecimal.valueOf(100), "color", "material", "m2", true, 100.0, 100.0, "brand");
        assertNotNull(product);
        assertInstanceOf(BuildingMaterial.class, product.asBuildingMaterial());
    }

    @Test
    void canCreateHouseholdEquipment() {
        Product product = new Product("name", "description", BigDecimal.valueOf(100), "color", "material", null, true, null, null, null);
        assertNotNull(product);
        assertInstanceOf(HouseholdEquipment.class, product.asHouseholdEquipment());
    }

    @Test
    void canCreateTool() {
        Product product = new Product("name", "description", BigDecimal.valueOf(100), "color", "material", null, null, 100.0, 100.0, "brand");
        assertNotNull(product);
        assertInstanceOf(Tool.class, product.asTool());
    }

    @Test
    void canCreateBuildingMaterialWithTool() {
        Product product = new Product("name", "description", BigDecimal.valueOf(100), "color", "material", "m2", null, 100.0, 100.0, "brand");
        assertNotNull(product);
        assertInstanceOf(BuildingMaterial.class, product.asBuildingMaterial());
        assertInstanceOf(Tool.class, product.asTool());
    }

    @Test
    void canCreateHouseholdEquipmentWithTool() {
        Product product = new Product("name", "description", BigDecimal.valueOf(100), "color", "material", null, true, 100.0, 100.0, "brand");
        assertNotNull(product);
        assertInstanceOf(HouseholdEquipment.class, product.asHouseholdEquipment());
        assertInstanceOf(Tool.class, product.asTool());
    }

    @Test
    void canCreateBuildingMaterialWithHouseholdEquipment() {
        Product product = new Product("name", "description", BigDecimal.valueOf(100), "color", "material", "m2", true, null, null, null);
        assertNotNull(product);
        assertInstanceOf(BuildingMaterial.class, product.asBuildingMaterial());
        assertInstanceOf(HouseholdEquipment.class, product.asHouseholdEquipment());
    }

    @Test
    void canCreateBuildingMaterialWithHouseholdEquipmentAndTool() {
        Product product = new Product("name", "description", BigDecimal.valueOf(100), "color", "material", "m2", true, 100.0, 100.0, "brand");
        assertNotNull(product);
        assertInstanceOf(BuildingMaterial.class, product.asBuildingMaterial());
        assertInstanceOf(HouseholdEquipment.class, product.asHouseholdEquipment());
        assertInstanceOf(Tool.class, product.asTool());
    }
}
