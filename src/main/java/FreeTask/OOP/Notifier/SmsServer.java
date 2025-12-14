package FreeTask.OOP.Notifier;

public class SmsServer extends Notifier {
    @Override
    public void sendMessage() {
        System.out.println("рассылка по СМС «Внимание: гроза»");
            }
}
