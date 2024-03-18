package zad18;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UsernameEmails {

    private final Map<String, String> usernameEmails;

    public UsernameEmails() {
        this.usernameEmails = new HashMap<>();
    }

    public static void main(String[] args) {
        UsernameEmails usernameEmails = new UsernameEmails();
        usernameEmails.addUsernameEmail("john", "john@doe.com");
        usernameEmails.addUsernameEmail("alice", "alice@mallory.com");
        usernameEmails.addUsernameEmail("bob", "bob@rob.com");

        usernameEmails.removeUsernameEmail("alice");

        for (Map.Entry<String, String> entry : usernameEmails.getUsernameEmails().entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void addUsernameEmail(String username, String email) {
        usernameEmails.put(username, email);
    }

    public void removeUsernameEmail(String username) {
        usernameEmails.remove(username);
    }

    public String getEmail(String username) {
        return usernameEmails.get(username);
    }

    public Map<String, String> getUsernameEmails() {
        return Collections.unmodifiableMap(usernameEmails);
    }
}
