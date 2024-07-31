package model.account;

import java.util.HashSet;
import java.util.Set;

public class Account {

    private static final Set<GuestAccount> allGuestAccounts = new HashSet<>();
    private static final Set<RegisteredAccount> allRegisteredAccounts = new HashSet<>();
    private static final Set<PremiumAccount> allPremiumAccounts = new HashSet<>();
    private String firstName;
    private String lastName;
    private GuestAccount guestAccount;
    private RegisteredAccount registeredAccount;
    private PremiumAccount premiumAccount;

    public Account(String firstName, String lastName, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        guestAccount = GuestAccount.createGuestAccount(this, phoneNumber);
    }

    public Account(String firstName, String lastName, String login, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        registeredAccount = RegisteredAccount.createRegisteredAccount(this, login, password);
    }

    public Account(String firstName, String lastName, String loyaltyCardNumber, String loyaltyCardPIN, int discountPercentage) {
        setFirstName(firstName);
        setLastName(lastName);
        premiumAccount = PremiumAccount.createPremiumAccount(this, loyaltyCardNumber, loyaltyCardPIN, discountPercentage);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public void toGuestAccount(String phoneNumber) {
        if (guestAccount != null) {
            throw new IllegalStateException("Account is already a guest account");
        }
        setGuestAccount(GuestAccount.createGuestAccount(this, phoneNumber));
        if (registeredAccount != null) {
            removeRegisteredAccount();
        }
        if (premiumAccount != null) {
            removePremiumAccount();
        }
    }

    public void toRegisteredAccount(String login, String password) {
        if (registeredAccount != null) {
            throw new IllegalStateException("Account is already a registered account");
        }
        setRegisteredAccount(RegisteredAccount.createRegisteredAccount(this, login, password));
        if (guestAccount != null) {
            removeGuestAccount();
        }
        if (premiumAccount != null) {
            removePremiumAccount();
        }
    }

    public void toPremiumAccount(String loyaltyCardNumber, String loyaltyCardPIN, int discountPercentage) {
        if (premiumAccount != null) {
            throw new IllegalStateException("Account is already a premium account");
        }
        setPremiumAccount(PremiumAccount.createPremiumAccount(this, loyaltyCardNumber, loyaltyCardPIN, discountPercentage));
        if (guestAccount != null) {
            removeGuestAccount();
        }
        if (registeredAccount != null) {
            removeRegisteredAccount();
        }
    }

    public GuestAccount asGuestAccount() {
        if (guestAccount == null) {
            throw new IllegalStateException("Account is not a guest account");
        }
        return guestAccount;
    }

    public RegisteredAccount asRegisteredAccount() {
        if (registeredAccount == null) {
            throw new IllegalStateException("Account is not a registered account");
        }
        return registeredAccount;
    }

    public PremiumAccount asPremiumAccount() {
        if (premiumAccount == null) {
            throw new IllegalStateException("Account is not a premium account");
        }
        return premiumAccount;
    }

    public void removeGuestAccount() {
        if (guestAccount != null) {
            GuestAccount guestAccount = this.guestAccount;
            this.guestAccount = null;
            guestAccount.remove();
        }
    }

    public void removeRegisteredAccount() {
        if (registeredAccount != null) {
            RegisteredAccount registeredAccount = this.registeredAccount;
            this.registeredAccount = null;
            registeredAccount.remove();
        }
    }

    public void removePremiumAccount() {
        if (premiumAccount != null) {
            PremiumAccount premiumAccount = this.premiumAccount;
            this.premiumAccount = null;
            premiumAccount.remove();
        }
    }

    public void setGuestAccount(GuestAccount guestAccount) {
        if (guestAccount == null) {
            throw new IllegalArgumentException("Cannot add null guest account");
        }
        if (this.guestAccount == guestAccount && guestAccount.getAccount() == this) {
            return;
        }
        if (allGuestAccounts.contains(guestAccount)) {
            throw new IllegalArgumentException("Guest account already belongs to another account");
        }
        if (guestAccount.getAccount() != this) {
            throw new IllegalArgumentException("Guest account does not belong to this account");
        }
        if (this.guestAccount != null) {
            throw new IllegalArgumentException("Account already has a guest account");
        }
        this.guestAccount = guestAccount;
        allGuestAccounts.add(guestAccount);
    }

    public void setRegisteredAccount(RegisteredAccount registeredAccount) {
        if (registeredAccount == null) {
            throw new IllegalArgumentException("Cannot add null registered account");
        }
        if (this.registeredAccount == registeredAccount && registeredAccount.getAccount() == this) {
            return;
        }
        if (allRegisteredAccounts.contains(registeredAccount)) {
            throw new IllegalArgumentException("Registered account already belongs to another account");
        }
        if (registeredAccount.getAccount() != this) {
            throw new IllegalArgumentException("Registered account does not belong to this account");
        }
        if (this.registeredAccount != null) {
            throw new IllegalArgumentException("Account already has a registered account");
        }
        this.registeredAccount = registeredAccount;
        allRegisteredAccounts.add(registeredAccount);
    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        if (premiumAccount == null) {
            throw new IllegalArgumentException("Cannot add null premium account");
        }
        if (this.premiumAccount == premiumAccount && premiumAccount.getAccount() == this) {
            return;
        }
        if (allPremiumAccounts.contains(premiumAccount)) {
            throw new IllegalArgumentException("Premium account already belongs to another account");
        }
        if (premiumAccount.getAccount() != this) {
            throw new IllegalArgumentException("Premium account does not belong to this account");
        }
        if (this.premiumAccount != null) {
            throw new IllegalArgumentException("Account already has a premium account");
        }
        this.premiumAccount = premiumAccount;
        allPremiumAccounts.add(premiumAccount);
    }
}
