package model.account;

public class GuestAccount {

    private Account account;
    private String phoneNumber;

    private GuestAccount(Account account, String phoneNumber) {
        setAccount(account);
        setPhoneNumber(phoneNumber);
    }

    public static GuestAccount createGuestAccount(Account account, String phoneNumber) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        return new GuestAccount(account, phoneNumber);
    }

    public Account getAccount() {
        return account;
    }

    private void setAccount(Account account) {
        this.account = account;
        account.setGuestAccount(this);
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

    public void remove() {
        if (account != null) {
            Account account = this.account;
            this.account = null;
            account.removeGuestAccount();
        }
    }
}
