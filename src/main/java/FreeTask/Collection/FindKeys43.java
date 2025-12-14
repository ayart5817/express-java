package FreeTask.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindKeys43 {
    static Map<String, String> map = new HashMap<>();

    static {
        map.put("a", "10");
        map.put("b", "20");
        map.put("c", "30");
        map.put("d", "40");
        map.put("e", "30");
    }

    public static void findKeys(Map<String, String> map, Character diapason1, Character diapason2 ) {
        List<String> result = new ArrayList<>();
        for (String str: map.keySet()) {
          if  (str.charAt(0) >= diapason1 && str.charAt(0) <= diapason2) {
              result.add(str);
          }
        }
        System.out.println("результат поиска по диапазону " +diapason1 + " и " + diapason2 + " — " + result);
    }

    public static void findKeyForValue(String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(str)) {
                System.out.print(entry.getKey() + " — Ключи которые подошли \n");
            }
        }
    }

    static void main(String[] args) {
        findKeys(map, 'a', 'b');
        findKeyForValue("30");
    }
}
