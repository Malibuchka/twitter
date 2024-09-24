package PACKAGE_NAME;

import java.util.Random;

// Класс продюсера, который генерирует сообщения
class Producer {
    private final String name;
    private final String[] messages;
    private long lastMessageTime = 0; // Время последнего отправленного сообщения

    public Producer(String name, String[] messages) {
        this.name = name;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }
    //TODO: вопрос можно ли добавлять такую отсебячитину
    // Возвращает случайное сообщение с проверкой на интервал в 60 секунд
    public  String getMessage() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMessageTime >= 60 * 1000) {
            Random rand = new Random();
            lastMessageTime = currentTime;
            return messages[rand.nextInt(messages.length)];
        }
        return null; // Не отправлять сообщение, если прошло меньше 60 секунд
    }
}


