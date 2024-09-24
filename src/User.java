package PACKAGE_NAME;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private Set<Producer> subscriptions;

    public User(String username) {
        this.username = username;
        this.subscriptions = new HashSet<>();
    }

    public void subscribe(Producer producer) {
        subscriptions.add(producer);
        System.out.println(username + " subscribed to " + producer.getName());
    }

    public Set<Producer> getSubscriptions() {
        return subscriptions;
    }

    public String getUsername() {
        return username;
    }

    public void showSubscriptions() {
        if (subscriptions.isEmpty()) {
            System.out.println(username + " is not subscribed to any producers.");
        } else {
            System.out.println(username + " is subscribed to:");
            for (Producer producer : subscriptions) {
                System.out.println("- " + producer.getName());
            }
        }
    }
}


