package model.product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Product {

    private static final Set<Product> extent = new HashSet<>();
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final int TAX_RATE = 23;
    private static final Set<String> availableMaterials = new HashSet<>();
    private final Set<String> materials = new HashSet<>();
    private String name;
    private String description;
    private BigDecimal netPrice;
    private String color;
    private BuildingMaterial buildingMaterial;
    private HouseholdEquipment householdEquipment;
    private Tool tool;

    public Product(String name, String description, BigDecimal netPrice, String color, String material, String baseUnit, Boolean isSoldForSelfAssembly, Double wattage, Double voltage, String brand) {
        setName(name);
        setDescription(description);
        setNetPrice(netPrice);
        setColor(color);
        addMaterial(material);
        if (baseUnit != null) {
            buildingMaterial = BuildingMaterial.createBuildingMaterial(this, baseUnit);
        }
        if (brand != null) {
            tool = Tool.createTool(this, wattage, voltage, brand);
        } else if (wattage != null || voltage != null) {
            throw new IllegalArgumentException("Brand must be provided if product is a tool");
        }
        if (isSoldForSelfAssembly != null) {
            householdEquipment = HouseholdEquipment.createHouseholdEquipment(this, isSoldForSelfAssembly);
        }
        if (buildingMaterial == null && householdEquipment == null && tool == null) {
            throw new IllegalArgumentException("Product must be either a building material, household equipment or a tool");
        }
        extent.add(this);
    }

    public static Set<Product> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    public static void addAvailableMaterial(String material) {
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        availableMaterials.add(material);
    }

    public static void removeAvailableMaterial(String material) {
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        if (!availableMaterials.contains(material)) {
            throw new IllegalArgumentException("Material not found");
        }
        availableMaterials.remove(material);
    }

    public static Set<String> getAvailableMaterials() {
        return Collections.unmodifiableSet(availableMaterials);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("Description is too long");
        }
        this.description = description;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        if (netPrice == null || netPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Net price must be greater than 0");
        }
        this.netPrice = netPrice;
    }

    public BigDecimal getGrossPrice() {
        return netPrice.multiply(BigDecimal.valueOf(1 + TAX_RATE / 100.0));
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null) {
            this.color = null;
            return;
        }
        if (color.isBlank()) {
            throw new IllegalArgumentException("Color cannot blank");
        }
        this.color = color;
    }

    public Set<String> getMaterials() {
        return Collections.unmodifiableSet(materials);
    }

    public void addMaterial(String material) {
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        if (!availableMaterials.contains(material)) {
            throw new IllegalArgumentException("Material unknown");
        }
        materials.add(material);
    }

    public void removeMaterial(String material) {
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        if (!materials.contains(material)) {
            throw new IllegalArgumentException("Material not found");
        }
        if (materials.size() == 1) {
            throw new IllegalStateException("Product must have at least one material");
        }
        materials.remove(material);
    }

    public BuildingMaterial asBuildingMaterial() {
        if (buildingMaterial == null) {
            throw new IllegalStateException("Product is not a building material");
        }
        return buildingMaterial;
    }

    public HouseholdEquipment asHouseholdEquipment() {
        if (householdEquipment == null) {
            throw new IllegalStateException("Product is not a household equipment");
        }
        return householdEquipment;
    }

    public Tool asTool() {
        if (tool == null) {
            throw new IllegalStateException("Product is not a tool");
        }
        return tool;
    }
}
