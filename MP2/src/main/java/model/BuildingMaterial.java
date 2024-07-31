package model;

import java.math.BigDecimal;

public class BuildingMaterial extends Product {

    private Dimensions dimensions;

    public BuildingMaterial(String name, BigDecimal netPrice, String description, Dimensions dimensions) {
        super(name, netPrice, description);
        setDimensions(dimensions);
    }

    public BuildingMaterial(String name, BigDecimal netPrice, Dimensions dimensions) {
        this(name, netPrice, null, dimensions);
    }

    public Dimensions getDimensions() {
        return Dimensions.copyOf(dimensions);
    }

    public void setDimensions(Dimensions dimensions) {
        if (dimensions == null) {
            throw new IllegalArgumentException("Dimensions cannot be null");
        } else if (dimensions.length() <= 0 || dimensions.width() <= 0 || dimensions.height() < 0) {
            throw new IllegalArgumentException("Length and width must be greater than 0, height must be 0 or greater");
        }
        this.dimensions = Dimensions.copyOf(dimensions);
    }

    @Override
    public String toString() {
        return "Building material: " + "name='" + getName() + "', net price='" + getNetPrice() + (getDescription() != null ? "', description='" + getDescription() : "") + "', " + dimensions;
    }
}
