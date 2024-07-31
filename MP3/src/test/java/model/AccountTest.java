package model;

import model.account.Account;
import model.account.GuestAccount;
import model.account.PremiumAccount;
import model.account.RegisteredAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {


    @Test
    void canCreateGuestAccount() {
        Account account = new Account("name", "surname", "123456789");
        assertNotNull(account);
        assertEquals(account.asGuestAccount().getClass(), GuestAccount.class);
    }

    @Test
    void canCreateRegisteredAccount() {
        Account account = new Account("name", "surname", "login", "password");
        assertNotNull(account);
        assertEquals(account.asRegisteredAccount().getClass(), RegisteredAccount.class);
    }

    @Test
    void canCreatePremiumAccount() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        assertNotNull(account);
        assertEquals(account.asPremiumAccount().getClass(), PremiumAccount.class);
    }

    @Test
    void canGetFirstName() {
        Account account = new Account("name", "surname", "123456789");
        assertEquals(account.getFirstName(), "name");
    }

    @Test
    void canGetLastName() {
        Account account = new Account("name", "surname", "123456789");
        assertEquals(account.getLastName(), "surname");
    }


    @Test
    void canConvertRegisteredAccountToGuestAccount() {
        Account account = new Account("name", "surname", "login", "password");
        account.toGuestAccount("123456789");
        assertNotNull(account);
        assertEquals(account.asGuestAccount().getClass(), GuestAccount.class);
    }

    @Test
    void canConvertPremiumAccountToGuestAccount() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        account.toGuestAccount("123456789");
        assertNotNull(account);
        assertEquals(account.asGuestAccount().getClass(), GuestAccount.class);
    }

    @Test
    void canConvertGuestAccountToRegisteredAccount() {
        Account account = new Account("name", "surname", "123456789");
        account.toRegisteredAccount("login", "password");
        assertNotNull(account);
        assertEquals(account.asRegisteredAccount().getClass(), RegisteredAccount.class);
    }

    @Test
    void canConvertPremiumAccountToRegisteredAccount() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        account.toRegisteredAccount("login", "password");
        assertNotNull(account);
        assertEquals(account.asRegisteredAccount().getClass(), RegisteredAccount.class);
    }

    @Test
    void canConvertGuestAccountToPremiumAccount() {
        Account account = new Account("name", "surname", "123456789");
        account.toPremiumAccount("1234567812345678", "1234", 10);
        assertNotNull(account);
        assertEquals(account.asPremiumAccount().getClass(), PremiumAccount.class);
    }

    @Test
    void canConvertRegisteredAccountToPremiumAccount() {
        Account account = new Account("name", "surname", "login", "password");
        account.toPremiumAccount("1234567812345678", "1234", 10);
        assertNotNull(account);
        assertEquals(account.asPremiumAccount().getClass(), PremiumAccount.class);
    }

    @Test
    void cannotConvertGuestAccountToGuestAccount() {
        Account account = new Account("name", "surname", "123456789");
        assertThrows(IllegalStateException.class, () -> account.toGuestAccount("123456789"));
    }

    @Test
    void cannotConvertRegisteredAccountToRegisteredAccount() {
        Account account = new Account("name", "surname", "login", "password");
        assertThrows(IllegalStateException.class, () -> account.toRegisteredAccount("login", "password"));
    }

    @Test
    void cannotConvertPremiumAccountToPremiumAccount() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        assertThrows(IllegalStateException.class, () -> account.toPremiumAccount("1234567812345678", "1234", 10));
    }

    @Test
    void cannotGetPremiumAccountFromGuestAccount() {
        Account account = new Account("name", "surname", "123456789");
        assertThrows(IllegalStateException.class, account::asPremiumAccount);
    }

    @Test
    void cannotGetRegisteredAccountFromGuestAccount() {
        Account account = new Account("name", "surname", "123456789");
        assertThrows(IllegalStateException.class, account::asRegisteredAccount);
    }

    @Test
    void cannotGetGuestAccountFromRegisteredAccount() {
        Account account = new Account("name", "surname", "login", "password");
        assertThrows(IllegalStateException.class, account::asGuestAccount);
    }

    @Test
    void cannotGetPremiumAccountFromRegisteredAccount() {
        Account account = new Account("name", "surname", "login", "password");
        assertThrows(IllegalStateException.class, account::asPremiumAccount);
    }

    @Test
    void cannotGetGuestAccountFromPremiumAccount() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        assertThrows(IllegalStateException.class, account::asGuestAccount);
    }

    @Test
    void cannotGetRegisteredAccountFromPremiumAccount() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        assertThrows(IllegalStateException.class, account::asRegisteredAccount);
    }

    @Test
    void canGetPhoneNumber() {
        Account account = new Account("name", "surname", "123456789");
        assertEquals(account.asGuestAccount().getPhoneNumber(), "123456789");
    }

    @Test
    void canGetLogin() {
        Account account = new Account("name", "surname", "login", "password");
        assertEquals(account.asRegisteredAccount().getLogin(), "login");
    }

    @Test
    void canGetPassword() {
        Account account = new Account("name", "surname", "login", "password");
        assertEquals(account.asRegisteredAccount().getPassword(), "password");
    }

    @Test
    void canGetLoyaltyCardNumber() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        assertEquals(account.asPremiumAccount().getLoyaltyCardNumber(), "1234567812345678");
    }

    @Test
    void canGetLoyaltyCardPIN() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        assertEquals(account.asPremiumAccount().getLoyaltyCardPIN(), "1234");
    }

    @Test
    void canGetDiscountPercentage() {
        Account account = new Account("name", "surname", "1234567812345678", "1234", 10);
        assertEquals(account.asPremiumAccount().getDiscountPercentage(), 10);
    }
}
