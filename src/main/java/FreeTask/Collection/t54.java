package FreeTask.Collection;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class t54 {
    static Map<String, String> parseCsvToMap(List<String> lines) {
        Map<String, String> result = new LinkedHashMap<>();
        for (String line : lines) {
            String[] l = line.trim().split(";");
            if (!l[0].isEmpty()) {
                result.put(l[0], l[1]);
            }
        }
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " ☺ " + entry.getValue());
        }
        return result;
    }


    static void main(String[] args) {
        List<String> line = Arrays.asList(
                "Имя;Анна",
                "Возраст;30",
                "Город;Москва");
        parseCsvToMap(line);
    }

}
