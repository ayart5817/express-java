package FreeTask.OOP.Notifier;

public class MobileApplication extends Notifier {
    @Override
    public void sendMessage() {
        System.out.println("Пуш уведомление «Снегопад, +2°C»");
    }
}
