package FreeTask.Collection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 56. Генерация таблицы пользователей с сортировкой по имени и ID
 * Создай структуру, позволяющую сортировать по имени, а при равенстве — по ID.
 */
public class SortedTable56 {

    // Правильно: ID → имя (ID уникален, имена могут повторяться)
    static Map<Integer, String> users = new HashMap<>();

    static {
        users.put(1, "ann");
        users.put(2, "ann");  // теперь оба "ann" сохранены!
        users.put(3, "a");
        users.put(5, "b");
        users.put(4, "b");
    }

    static void sorted() {
        // Сортируем: сначала по имени (значению), затем по ID (ключу)
        Map<Integer, String> sorted = users.entrySet().stream()
                .sorted(Map.Entry.<Integer, String>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,    // ключ — ID
                        Map.Entry::getValue,  // значение — имя
                        (e1, e2) -> e1,      // не должно быть дубликатов ID, но на всякий случай
                        LinkedHashMap::new    // сохраняем порядок
                ));

        // Выводим результат
        sorted.forEach((id, name) ->
                System.out.println("ID: " + id + ", Имя: " + name));
    }

    public static void main(String[] args) {
        System.out.println("До сортировки:");
        users.forEach((id, name) ->
                System.out.println("ID: " + id + ", Имя: " + name));

        System.out.println("\nПосле сортировки (по имени, затем по ID):");
        sorted();
    }
}