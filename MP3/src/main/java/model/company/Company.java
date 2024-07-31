package model.company;

public abstract class Company {

    private String name;
    private String address;
    private String NIP;

    public Company(String name, String address, String NIP) {
        setName(name);
        setAddress(address);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        if (NIP == null || NIP.isBlank()) {
            throw new IllegalArgumentException("NIP cannot be null or empty");
        }
        this.NIP = NIP;
    }
}
