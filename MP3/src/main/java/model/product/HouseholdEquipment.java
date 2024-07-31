package model.product;

import java.util.Collections;
import java.util.Set;

public class HouseholdEquipment {

    private final Product product;
    private Set<String> safetyCertificates;
    private boolean isSoldForSelfAssembly;

    private HouseholdEquipment(Product product, boolean isSoldForSelfAssembly) {
        this.product = product;
        setSoldForSelfAssembly(isSoldForSelfAssembly);
    }

    public static HouseholdEquipment createHouseholdEquipment(Product product, boolean isSoldForSelfAssembly) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (Product.getExtent().contains(product)) {
            throw new IllegalArgumentException("Product already exists");
        }
        return new HouseholdEquipment(product, isSoldForSelfAssembly);
    }

    public Product getProduct() {
        return product;
    }

    public Set<String> getSafetyCertificates() {
        return Collections.unmodifiableSet(safetyCertificates);
    }

    public void addSafetyCertificate(String certificate) {
        if (certificate == null || certificate.isBlank()) {
            throw new IllegalArgumentException("Cannot add null or empty certificate");
        }
        safetyCertificates.add(certificate);
    }

    public void removeSafetyCertificate(String certificate) {
        if (certificate == null || certificate.isBlank()) {
            throw new IllegalArgumentException("Cannot remove null or empty certificate");
        }
        safetyCertificates.remove(certificate);
    }

    public boolean isSoldForSelfAssembly() {
        return isSoldForSelfAssembly;
    }

    public void setSoldForSelfAssembly(boolean isSoldForSelfAssembly) {
        this.isSoldForSelfAssembly = isSoldForSelfAssembly;
    }
}
