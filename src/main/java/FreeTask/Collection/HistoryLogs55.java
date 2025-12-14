package FreeTask.Collection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryLogs55 {
    static Map<LocalDate, List<String>> history = new HashMap<>();

    static void addHistory(LocalDate date, String str) {
        history.computeIfAbsent(date, l -> new ArrayList<>()).add(str);

    }

    static void getAll() {
        for (Map.Entry<LocalDate, List<String>> entry : history.entrySet()) {
            System.out.println(entry);
        }
    }

    static void getHistory(LocalDate date) {
        System.out.println(history.get(date));
    }

    static void main(String[] args) {
        addHistory(LocalDate.of(2025, 10, 11), "Тест a");
        addHistory(LocalDate.of(2025, 10, 11), "Тест b");
        addHistory(LocalDate.of(2025, 10, 11), "Тест c");
        addHistory(LocalDate.of(2025, 10, 10), "Тест ab");
        addHistory(LocalDate.of(2025, 10, 10), "Тест ac");
        getHistory(LocalDate.of(2025, 10, 11));
        getAll();
    }
}
