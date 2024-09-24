package PACKAGE_NAME;


// Класс Twitter управляет лентой пользователя и реализует интерфейс Runnable для многопоточки

class Twitter implements Runnable {
    private final User user;
    private boolean isRunning = true;

    public Twitter(User user) {
        this.user = user;
    }
    // Останавливаем выполнение потока
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                for (Producer producer : user.getSubscriptions()) {
                    if (!isRunning) break; // Прерываем цикл, если поток остановлен
                    String message = producer.getMessage();
                    if (message != null) {
                        System.out.println("---------------------------------------------");
                        System.out.println(producer.getName() + ": " + message);
                    }
                    Thread.sleep(10 * 1000); // Задержка между любыми сообщениями 10 секунд
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Твиттинг прекращен.");
        }
    }
}