package PACKAGE_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Producer {
    private final String name;
    private final String[] messages;
    private long lastMessageTime = 0; // Время последнего отправленного сообщения
    private int likes = 0;
    private int dislikes = 0;
    private final List<Comment> comments = new ArrayList<>();

    public Producer(String name, String[] messages) {
        this.name = name;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMessageTime >= 60 * 1000) {
            Random rand = new Random();
            lastMessageTime = currentTime;
            return messages[rand.nextInt(messages.length)];
        }
        return null; // Не отправлять сообщение, если прошло меньше 60 секунд
    }

    public void addComment(String username, String comment) {
        comments.add(new Comment(username, comment));
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
