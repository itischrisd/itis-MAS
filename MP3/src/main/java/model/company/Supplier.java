package model.company;

public class Supplier extends Company {

    private String email;
    private String phoneNumber;
    private String bankAccount;

    public Supplier(String name, String address, String NIP) {
        super(name, address, NIP);
    }

    public Supplier(String name, String address, String NIP, String email, String phoneNumber, String bankAccount) {
        super(name, address, NIP);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setBankAccount(bankAccount);
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        if (!phoneNumber.matches("[0-9]{9}")) {
            throw new IllegalArgumentException("Phone number must contain 9 digits");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        if (bankAccount == null || bankAccount.isBlank()) {
            throw new IllegalArgumentException("Bank account cannot be null or empty");
        }
        if (!bankAccount.matches("[0-9]{26}")) {
            throw new IllegalArgumentException("Bank account must contain 26 digits");
        }
        this.bankAccount = bankAccount;
    }
}
