package zad18;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProductCatalogue {

    private final Set<Integer> productIds;

    public ProductCatalogue() {
        this.productIds = new HashSet<>();
    }

    public static void main(String[] args) {
        ProductCatalogue productCatalogue = new ProductCatalogue();
        productCatalogue.addProduct(1);
        productCatalogue.addProduct(2);
        productCatalogue.addProduct(3);
        productCatalogue.addProduct(4);
        productCatalogue.addProduct(5);

        productCatalogue.removeProduct(3);

        for (int productId : productCatalogue.getProductIds()) {
            System.out.println(productId);
        }
    }

    public boolean addProduct(int productId) {
        return productIds.add(productId);
    }

    public boolean removeProduct(int productId) {
        return productIds.remove(productId);
    }

    public Set<Integer> getProductIds() {
        return Collections.unmodifiableSet(productIds);
    }
}
