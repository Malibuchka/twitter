package PACKAGE_NAME;

import java.util.*;



// Основной класс для запуска программы
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, User> users = new HashMap<>(); // hashmap для user([хранение по их именам)
        Map<String, Producer> producers = new HashMap<>();//hasmap для producers

        // Создаем пользователей
        System.out.print("Введите кол-во пользователей: ");
        int userCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < userCount; i++) {
            System.out.print("Введите имя(имена) пользователя(пользователей): ");
            String username = scanner.nextLine();
            User user = new User(username);
            users.put(username, user);
        }

        // Выбираем пользователя, чью ленту отображать
        System.out.print("Введите имя пользователя для вывода его ленты: ");
        String selectedUser = scanner.nextLine();
        User currentUser = users.get(selectedUser);

        if (currentUser == null) {
            System.out.println("Пользователь не найден .");
            return;
        }

        currentUser.showSubscriptions();

        // Запускаем твиттинг для выбранного пользователя
        Twitter twitter = new Twitter(currentUser);
        Thread feedThread = new Thread(twitter);
        feedThread.start(); // Запускаем поток для ленты


        while (true) {
            System.out.println("\n()--- МЕНЮ ---()");
            System.out.println("1. Добавить нового Продюсера");
            System.out.println("2. Подписать текущего пользователя к Продюсеру");
            System.out.println("3. Показать текущие подписки");
            System.out.println("4. Показать текущего пользователя");
            System.out.println("5. Выйти из псевдо Твиттера");
            System.out.println("6. Напишите имя Продюсера, к которому хотите оставить комментарий");
            System.out.println("7. Введите имя Продюсера, который хотите лайкнуть:");
            System.out.println("8. Введите имя Продюсера, который хотите дизлайкнуть");
            System.out.print("\n");
            String choice = scanner.nextLine();


            switch (choice) {
                case "1":
                    // Добавление нового продюсера
                    System.out.print("Введите имя Продюсера ");
                    String newProducerName = scanner.nextLine();
                    System.out.print("Введите сообщения для этого Продюсера "); // делим по запятым
                    String[] messages = scanner.nextLine().split(",");
                    Producer newProducer = new Producer(newProducerName, messages);
                    producers.put(newProducerName, newProducer);
                    System.out.println("Продюсер " + newProducerName + " добавлен.");
                    break;

                case "2":
                    // Подписка текущего пользователя на продюсера
                    System.out.println("Доступные продюсеры: " + String.join(", ", producers.keySet()));
                    System.out.print("Введите имя Продюсера на которого хотели бы подписаться: ");
                    String producerName = scanner.nextLine();
                    Producer producer = producers.get(producerName);

                    if (producer != null) {
                        currentUser.subscribe(producer);
                    } else {
                        System.out.println("Продюсер не найден");
                    }
                    break;

                case "3":
                    // Показать подписки текущего пользователя
                    System.out.println("Подписки текущего пользователя: " + currentUser.getUsername());
                    currentUser.showSubscriptions();
                    break;

                case "4":
                    //текущий user
                    System.out.println("Текущий пользователь: " + currentUser.getUsername());
                    break;

                case "5":
                    System.out.println("Выход...");
                    twitter.stop(); // Останавливаем выполнение потока
                    try {
                        feedThread.join(); // Ждем завершения потока
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                case "6":
                    // Комментарий к сообщению
                    System.out.println("Доступные продюсеры: " + String.join(", ", producers.keySet()));
                    System.out.print("Введите имя Продюсера, к которому хотите оставить комментарий: ");
                    String producerForComment = scanner.nextLine();
                    Producer producerForCommentObj = producers.get(producerForComment);
                    if (producerForCommentObj != null) {
                        System.out.print("Введите ваш комментарий: ");
                        String comment = scanner.nextLine();
                        currentUser.commentOnProducer(producerForCommentObj, comment);
                    } else {
                        System.out.println("Продюсер не найден");
                    }
                    break;
                case "7":
                    // Лайк к сообщению
                    System.out.println("Доступные продюсеры: " + String.join(", ", producers.keySet()));
                    System.out.print("Введите имя Продюсера, который хотите лайкнуть: ");
                    String producerForLike = scanner.nextLine();
                    Producer producerForLikeObj = producers.get(producerForLike);
                    if (producerForLikeObj != null) {
                        currentUser.likeProducer(producerForLikeObj);
                        System.out.println("\nВы лайкнули сообщения от " + producerForLike);
                    } else {
                        System.out.println("Продюсер не найден");
                    }
                    break;

                case "8":
                    // Дизлайк к сообщению
                    System.out.println("Доступные продюсеры: " + String.join(", ", producers.keySet()));

                    System.out.print("Введите имя Продюсера, который хотите дизлайкнуть: ");
                    String producerForDislike = scanner.nextLine();
                    Producer producerForDislikeObj = producers.get(producerForDislike);
                    if (producerForDislikeObj != null) {
                        currentUser.dislikeProducer(producerForDislikeObj);
                        System.out.println("\nВы дизлайкнули сообщения от " + producerForDislike);
                    } else {
                        System.out.println("Продюсер не найден");
                    }
                    break;

                default:
                    System.out.println("Нет такого варианта. Попробуйте пожалуйста еще раз");
                    break;
            }
        }
    }
}



