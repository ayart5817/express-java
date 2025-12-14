package FreeTask.Collection;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserLocalCounter {
    static Map<LocalDate, Integer> loginCount = new HashMap<>();

    public static void addLoginCount() {
        LocalDate date = LocalDate.now();
        loginCount.put(date, loginCount.getOrDefault(date, 0) + 1);
    }

    public static void login(String user) {
        addLoginCount();
        System.out.println("Пользователь авторизован" + user);
    }

    static void main(String[] args) {
        UserLocalCounter.login("Андрей");
        UserLocalCounter.login("Василий");
        for (Map.Entry<LocalDate, Integer> entry : loginCount.entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }
    }

}

