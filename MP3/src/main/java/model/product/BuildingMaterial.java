package model.product;

public class BuildingMaterial {

    private final Product product;
    private String baseUnit;

    private BuildingMaterial(Product product, String baseUnit) {
        this.product = product;
        setBaseUnit(baseUnit);
    }

    public static BuildingMaterial createBuildingMaterial(Product product, String baseUnit) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (Product.getExtent().contains(product)) {
            throw new IllegalArgumentException("Product already exists");
        }
        return new BuildingMaterial(product, baseUnit);
    }

    public Product getProduct() {
        return product;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        if (baseUnit == null || baseUnit.isBlank()) {
            throw new IllegalArgumentException("Base unit cannot be null or empty");
        }
        this.baseUnit = baseUnit;
    }
}
