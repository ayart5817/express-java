package FreeTask.OOP.Notifier;

import java.util.Arrays;
import java.util.List;

public class Main {
    static void main() {
        List<Notifier> notifiers = Arrays.asList(
                new Radio(),
                new SmsServer(),
                new MobileApplication());

        NotifierStation notifierStation = new NotifierStation();
        for (Notifier n:notifiers) {
            notifierStation.pushSends(n);
        }
    }
}

