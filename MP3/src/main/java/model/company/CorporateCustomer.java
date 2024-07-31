package model.company;

public class CorporateCustomer extends Company implements ICustomer {

    private String email;
    private String REGON;

    public CorporateCustomer(String name, String address, String NIP, String email, String REGON) {
        super(name, address, NIP);
        setEmail(email);
        setREGON(REGON);
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Email must be in a valid format");
        }
        this.email = email;
    }

    public String getREGON() {
        return REGON;
    }

    public void setREGON(String REGON) {
        if (REGON == null) {
            this.REGON = null;
            return;
        }
        if (REGON.isBlank()) {
            throw new IllegalArgumentException("REGON cannot be blank");
        }
        if (REGON.matches("[0-9]{9}")) {
            throw new IllegalArgumentException("REGON must be 9 digits long");
        }
        this.REGON = REGON;
    }
}
