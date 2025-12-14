package FreeTask.Collection;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

public class GetMaximumLength {
    /**
     * 44. Получение самых длинных строк из списка
     * Выведи все строки максимальной длины.
     */
    static List<String> myList = new ArrayList<>(List.of("adad2", "adad3", "aiokjk", "apoiwdjoawdwd", "adkawd"));

    static void getMaxLength() {
        String result = "";
        Map<String, Integer> countMap = new TreeMap<>();
        for (String str : myList) {
            int n = str.length();
            countMap.put(str, n);
        }
        int max =0;

        for (Map.Entry<String, Integer> entry: countMap.entrySet()) {

            if (entry.getValue() > max) {
                result = entry.getKey();
            }

        }
        System.out.println(countMap.get(result) + " — " + result);
    }

    static void main(String[] args) {
getMaxLength();
    }
}
