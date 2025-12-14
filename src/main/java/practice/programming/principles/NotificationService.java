package practice.programming.principles;

public class NotificationService {
    private final MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void sendNotification(String message) {
        sender.send(message);
    }
}

class EmailSender implements MessageSender {
    public void send(String message) {
        System.out.println("Отправка email: " + message);
    }
}

interface MessageSender {
    void send(String message);
}