package model;

import java.util.*;

public class Alley extends ExtensiatedObject {

    private final Set<Product> products = new HashSet<>();
    private Section section;
    private int number;

    private Alley(int number, Section section) {
        super();
        setNumber(number);
        setSection(section);
    }

    public static Alley createAlley(int number, Section section) {
        if (section == null) {
            throw new IllegalArgumentException("Section cannot be null");
        }
        return new Alley(number, section);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }
        try {
            if (ExtensiatedObject.getExtent(Alley.class).stream().anyMatch(a -> a.getNumber() == number)) {
                throw new IllegalArgumentException("Alley with this number already exists");
            }
        } catch (ClassNotFoundException ignored) {
        }
        this.number = number;
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Section getSection() {
        return section;
    }

    private void setSection(Section section) {
        this.section = section;
        this.section.addAlley(this);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot add null product.");
        }
        if (!products.contains(product)) {
            products.add(product);
            product.setAlley(this);
        }
    }

    public void removeProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot remove null product.");
        }
        if (products.contains(product)) {
            products.remove(product);
            product.setAlley(null);
        }
    }

    public void removeAlley() {
        if (this.section != null) {
            Section section = this.section;
            this.section = null;
            section.removeAlley(this);
            List<Product> productsToRemove = new ArrayList<>(products);
            productsToRemove.forEach(p -> p.setAlley(null));
            ExtensiatedObject.removeObject(this);
        }
    }
}
