package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product extends ExtensiatedObject {

    private static double taxRate = 0.23;
    private String name;
    private BigDecimal netPrice;
    private String description;

    public Product(String name, BigDecimal netPrice, String description) {
        super();
        setName(name);
        setNetPrice(netPrice);
        setDescription(description);
    }

    public Product(String name, BigDecimal netPrice) {
        this(name, netPrice, null);
    }

    public static double getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(double taxRate) {
        if (taxRate < 0) {
            throw new IllegalArgumentException("Tax rate cannot be less than 0");
        }
        Product.taxRate = taxRate;
    }

    public static BigDecimal calculateNetValueOfAllProducts() throws ClassNotFoundException {
        BigDecimal sum = BigDecimal.ZERO;
        Iterable<Product> products;

        try {
            products = ExtensiatedObject.getExtent(Product.class);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("The extent of the class Product could not be found");
        }

        for (Product product : products) {
            sum = sum.add(product.getNetPrice());
        }

        return sum;
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

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        if (netPrice == null || netPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Net price cannot be null or less than or equal to 0");
        }
        this.netPrice = netPrice;
    }

    public BigDecimal getGrossPrice() {
        return netPrice.add(netPrice.multiply(BigDecimal.valueOf(taxRate))).setScale(2, RoundingMode.HALF_UP);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank text");
        }
        this.description = description;
    }

    public BigDecimal sell() {
        BigDecimal sellingPrice = getGrossPrice();
        ExtensiatedObject.removeObject(this);
        return sellingPrice;
    }

    public BigDecimal sell(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 1) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 1");
        }
        BigDecimal discount = getGrossPrice().multiply(BigDecimal.valueOf(discountPercentage)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal sellingPrice = getGrossPrice().subtract(discount);
        ExtensiatedObject.removeObject(this);
        return sellingPrice;
    }

    @Override
    public String toString() {
        return "Product: " + "name='" + name + "', net price='" + netPrice + (description != null ? "', description='" + description : "") + "'";
    }
}
