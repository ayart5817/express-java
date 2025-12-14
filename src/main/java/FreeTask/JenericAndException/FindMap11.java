package FreeTask.JenericAndException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindMap11 {
    static Optional<Integer> find(Map<String, Integer> map, String key) {
        if (key == null || key.isEmpty()) {
            return Optional.empty();
        }
        if (map == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(map.get(key));
    }


    static void main(String[] args) {
        Map<String, Integer> m1 = new HashMap<>(Map.of("Строка", 10, "длинна", 673));
        System.out.println(find(m1, "Строка"));
        Map<String, Integer> m2 = new HashMap<>();
        // find(m2, "Строка");
        System.out.println(find(m1, ""));
        System.out.println(find(m1, ""));

    }
}
