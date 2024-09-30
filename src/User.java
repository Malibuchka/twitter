package PACKAGE_NAME;

import java.util.HashSet;
import java.util.Set;

class User {
    private final String username;
    private final Set<Producer> subscriptions;

    public User(String username) {
        this.username = username;
        this.subscriptions = new HashSet<>();
    }

    public void subscribe(Producer producer) {
        subscriptions.add(producer);
        System.out.println(username + " подписана на: " + producer.getName());
    }

    public Set<Producer> getSubscriptions() {
        return subscriptions;
    }

    public String getUsername() {
        return username;
    }

    public void showSubscriptions() {
        if (subscriptions.isEmpty()) {
            System.out.println("___________________________");
            System.out.println(username + " не подписан ни на каких Продюсеров");
        } else {
            System.out.println(username + " подписана на:");
            for (Producer producer : subscriptions) {
                System.out.println("- " + producer.getName());
            }
        }
    }

    public void commentOnProducer(Producer producer, String comment) {
        producer.addComment(username, comment);
    }

    public void likeProducer(Producer producer) {
        producer.like();
    }

    public void dislikeProducer(Producer producer) {
        producer.dislike();
    }
}
