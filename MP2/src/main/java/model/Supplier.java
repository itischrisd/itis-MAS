package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Supplier extends ExtensiatedObject {

    private final Set<Offer> offers = new HashSet<>();
    private String name;
    private long NIP;

    public Supplier(String name, long NIP) {
        super();
        setName(name);
        setNIP(NIP);
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

    public long getNIP() {
        return NIP;
    }

    public void setNIP(long NIP) {
        if (NIP < 1000000000 || NIP > 9999999999L) {
            throw new IllegalArgumentException("NIP has to be a 10-digit number");
        }
        this.NIP = NIP;
    }

    public Set<Offer> getOffers() {
        return Collections.unmodifiableSet(offers);
    }

    public void addOffer(Offer offer) {
        if (offer == null) {
            throw new IllegalArgumentException("Cannot add null offer");
        }
        if (offer.getSupplier() == this) {
            offers.add(offer);
        } else {
            throw new IllegalArgumentException("Offer does not belong to this supplier");
        }
    }

    public void removeOffer(Offer offer) {
        if (offer == null) {
            throw new IllegalArgumentException("Cannot remove null offer");
        }
        if (this.offers.contains(offer)) {
            this.offers.remove(offer);
            offer.removeOffer();
        }
    }
}
