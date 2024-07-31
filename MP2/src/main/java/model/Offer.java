package model;

import java.math.BigDecimal;

public class Offer extends ExtensiatedObject {

    private BigDecimal price;
    private int minimumQuantity;

    private Supplier supplier;
    private Product product;

    public Offer(BigDecimal price, int minimumQuantity, Supplier supplier, Product product) {
        super();
        setPrice(price);
        setMinimumQuantity(minimumQuantity);
        if (supplier == null || product == null) {
            throw new IllegalArgumentException("Supplier and product cannot be null");
        }
        if (supplier.getOffers().stream().anyMatch(o -> o.getProduct() == product)) {
            throw new IllegalArgumentException("Supplier already has an offer for this product");
        }
        this.supplier = supplier;
        this.product = product;
        this.supplier.addOffer(this);
        this.product.addOffer(this);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        if (minimumQuantity < 0) {
            throw new IllegalArgumentException("Minimum quantity cannot be negative");
        }
        this.minimumQuantity = minimumQuantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void removeOffer() {
        if (this.supplier != null) {
            Product product = this.product;
            Supplier supplier = this.supplier;
            this.product = null;
            this.supplier = null;
            supplier.removeOffer(this);
            product.removeOffer(this);
            ExtensiatedObject.removeObject(this);
        }

    }
}
