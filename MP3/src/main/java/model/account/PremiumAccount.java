package model.account;

public class PremiumAccount {

    private Account account;
    private String loyaltyCardNumber;
    private String loyaltyCardPIN;
    private int discountPercentage;

    private PremiumAccount(Account account, String loyaltyCardNumber, String loyaltyCardPIN, int discountPercentage) {
        setAccount(account);
        setLoyaltyCardNumber(loyaltyCardNumber);
        setLoyaltyCardPIN(loyaltyCardPIN);
        setDiscountPercentage(discountPercentage);
    }

    public static PremiumAccount createPremiumAccount(Account account, String loyaltyCardNumber, String loyaltyCardPIN, int discount) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        return new PremiumAccount(account, loyaltyCardNumber, loyaltyCardPIN, discount);
    }

    public Account getAccount() {
        return account;
    }

    private void setAccount(Account account) {
        this.account = account;
        account.setPremiumAccount(this);
    }

    public String getLoyaltyCardNumber() {
        return loyaltyCardNumber;
    }

    public void setLoyaltyCardNumber(String loyaltyCardNumber) {
        if (loyaltyCardNumber == null || loyaltyCardNumber.isBlank()) {
            throw new IllegalArgumentException("Loyalty card number cannot be null or empty");
        }
        if (!loyaltyCardNumber.matches("[0-9]{16}")) {
            throw new IllegalArgumentException("Loyalty card number must contain 16 digits");
        }
        this.loyaltyCardNumber = loyaltyCardNumber;
    }

    public String getLoyaltyCardPIN() {
        return loyaltyCardPIN;
    }

    public void setLoyaltyCardPIN(String loyaltyCardPIN) {
        if (loyaltyCardPIN == null || loyaltyCardPIN.isBlank()) {
            throw new IllegalArgumentException("Loyalty card PIN cannot be null or empty");
        }
        if (!loyaltyCardPIN.matches("[0-9]{4}")) {
            throw new IllegalArgumentException("Loyalty card PIN must contain 4 digits");
        }
        this.loyaltyCardPIN = loyaltyCardPIN;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        this.discountPercentage = discountPercentage;
    }

    public void remove() {
        if (account != null) {
            Account account = this.account;
            this.account = null;
            account.removePremiumAccount();
        }
    }
}
