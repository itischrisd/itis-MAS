package programming_tasks.zad18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clients {

    private final List<String> clients;

    public Clients() {
        clients = new ArrayList<>();
    }

    public static void main(String[] args) {
        Clients clients = new Clients();
        clients.addClient("John");
        clients.addClient("Alice");
        clients.addClient("Bob");
        clients.addClient("Eve");
        clients.addClient("Mallory");

        clients.removeClient("Mallory");

        for (String client : clients.getClients()) {
            System.out.println(client);
        }
    }

    public void addClient(String client) {
        clients.add(client);
    }

    public void removeClient(String client) {
        clients.remove(client);
    }

    public List<String> getClients() {
        return Collections.unmodifiableList(clients);
    }
}
