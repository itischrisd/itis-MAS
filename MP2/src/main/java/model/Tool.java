package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tool extends Product {

    private final List<String> manufacturingMaterials = new ArrayList<>();

    public Tool(String name, BigDecimal netPrice, String description, String manufacturingMaterial) {
        super(name, netPrice, description);
        addManufacturingMaterial(manufacturingMaterial);
    }

    public Tool(String name, BigDecimal netPrice, String manufacturingMaterial) {
        super(name, netPrice);
        addManufacturingMaterial(manufacturingMaterial);
    }

    public List<String> getManufacturingMaterials() {
        return Collections.unmodifiableList(manufacturingMaterials);
    }

    public void addManufacturingMaterial(String material) {
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        if (manufacturingMaterials.contains(material)) {
            throw new IllegalArgumentException("Material already exists");
        }
        manufacturingMaterials.add(material);
    }

    public void removeManufacturingMaterial(String material) {
        if (manufacturingMaterials.size() == 1) {
            throw new IllegalArgumentException("Cannot remove the last material");
        }
        if (!manufacturingMaterials.contains(material)) {
            throw new IllegalArgumentException("Material not found");
        }
        manufacturingMaterials.remove(material);
    }

    @Override
    public String toString() {
        return "Tool: " + "name='" + getName() + "', net price='" + getNetPrice() + (getDescription() != null ? "', description='" + getDescription() : "") + "'" + ", manufacturing materials=" + manufacturingMaterials.stream().map(e -> e + ", ").reduce("", String::concat).replaceAll(", $", "");
    }
}
