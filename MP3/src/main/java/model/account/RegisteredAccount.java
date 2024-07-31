package model.account;

public class RegisteredAccount {

    private Account account;
    private String login;
    private String password;

    private RegisteredAccount(Account account, String login, String password) {
        setAccount(account);
        setLogin(login);
        setPassword(password);
    }

    public static RegisteredAccount createRegisteredAccount(Account account, String login, String password) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        return new RegisteredAccount(account, login, password);
    }

    public Account getAccount() {
        return account;
    }

    private void setAccount(Account account) {
        this.account = account;
        account.setRegisteredAccount(this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    public void remove() {
        if (account != null) {
            Account account = this.account;
            this.account = null;
            account.removeRegisteredAccount();
        }
    }
}
