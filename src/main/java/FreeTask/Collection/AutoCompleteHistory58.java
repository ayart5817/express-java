package FreeTask.Collection;

import java.util.*;

public class AutoCompleteHistory58 {

    static final Map<String, Boolean> history = new LinkedHashMap<>();

    static void addHistory(String str) {
        if (!str.trim().isEmpty()) {
            history.put(str, true);
        }
    }

    static List<String> getHistory(String prefix) {
        return history.keySet().stream()
                .filter(s -> s.toLowerCase().startsWith(prefix.toLowerCase()))
                .limit(5)
                .toList(); // Java 16+
    }

    static void inputStr() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку (или 'Exit' для выхода):");

        while (true) {
            System.out.print("> ");
            String str = scanner.nextLine();

            if ("Exit".equals(str)) {
                System.out.println("Выход.");
                break;
            }

            List<String> suggestions = getHistory(str);
            if (!str.trim().isEmpty()) {
                addHistory(str);
            }

            System.out.println("Подсказки: " + (suggestions.isEmpty() ? "нет" : suggestions));
            System.out.println("История: " + new ArrayList<>(history.keySet()));
            System.out.println();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        inputStr();
    }
}