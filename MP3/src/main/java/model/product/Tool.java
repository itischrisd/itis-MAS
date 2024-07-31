package model.product;

public class Tool {

    private final Product product;
    private Double wattage;
    private Double voltage;
    private String brand;

    private Tool(Product product, Double wattage, Double voltage, String brand) {
        this.product = product;
        setWattage(wattage);
        setVoltage(voltage);
        setBrand(brand);
    }

    public static Tool createTool(Product product, Double wattage, Double voltage, String brand) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (Product.getExtent().contains(product)) {
            throw new IllegalArgumentException("Product already exists");
        }
        return new Tool(product, wattage, voltage, brand);
    }

    public Product getProduct() {
        return product;
    }

    public Double getWattage() {
        return wattage;
    }

    public void setWattage(Double wattage) {
        if (wattage <= 0) {
            throw new IllegalArgumentException("Wattage must be greater than 0");
        }
        this.wattage = wattage;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        if (voltage <= 0) {
            throw new IllegalArgumentException("Voltage must be greater than 0");
        }
        this.voltage = voltage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        this.brand = brand;
    }
}
