package PACKAGE_NAME;

class Twitter implements Runnable {
    private final User user;
    private boolean isRunning = true;

    public Twitter(User user) {
        this.user = user;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                for (Producer producer : user.getSubscriptions()) {
                    if (!isRunning) break;
                    String message = producer.getMessage();
                    if (message != null) {

                        Thread.sleep(1*100);
                        System.out.println("---------------------------------------------");
                        System.out.println("Лента начала свою работу у Пользователя" + "(" +user.getUsername() + ")");
                        System.out.println("(Продюсер)" +producer.getName() + ": (смс)" + message);
                        System.out.println("Likes: " + producer.getLikes() + " | Dislikes: " + producer.getDislikes());

                        System.out.println("Comments:");
                        for (Comment comment : producer.getComments()) {
                            System.out.println(" " + comment + " \n");
                        }
                    }
                    Thread.sleep(10 * 1000); // Задержка между сообщениями

                }
            }
        } catch (InterruptedException e) {
            System.out.println("Твиттинг прекращен.");
        }
    }
}
