package FreeTask.Collection;

import java.util.HashMap;
import java.util.Map;

public class MultiValueCounter39 {
    /**
     * (Ключ -> (значение - количество))
     * Ключи с возможностью дублирования значений
     * К одному ключу можно привязать одно и то же значение несколько раз. Отображай количество каждого значения.
     *
     */

    private static final Map<String, Map<String, Integer>> myList = new HashMap<>();

    static void add(String key, String value) {
        Map<String, Integer> innerMap = myList.computeIfAbsent(key, v -> new HashMap<>());
        innerMap.put(value, innerMap.getOrDefault(value, 0) + 1);
    }

    static void getAll() {
        for (Map.Entry<String, Map<String, Integer>> entry1 : myList.entrySet()) {
            Map<String, Integer> innerMap = entry1.getValue();

            for (Map.Entry<String, Integer> entry2 : innerMap.entrySet()) {
                System.out.print(entry1.getKey() + " Ключ " + " Значение " + entry2.getKey() + " количество " + entry2.getValue() + "\n");

            }
        }

    }

    static void main(String[] args) {
        add("key1", "value1");
        add("key2", "value2.1");
        add("key2", "value2.1");
        add("key2", "value3");

        getAll();
    }

}
