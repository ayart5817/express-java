package FreeTask.Collection;

import java.util.HashMap;
import java.util.Map;

public class MapSum {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 10);
        map.put("b", 20);
        map.put("c", 30);
        map.put("d", 40);
        int sumValies = 0;
        for (Integer value : map.values()) {

            sumValies = sumValies + value;
        }
        System.out.println("Сумма всех значений в мапе "+sumValies);
    }

}