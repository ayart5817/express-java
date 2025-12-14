package FreeTask.Collection;

import java.util.HashMap;
import java.util.Map;

public class ComparingTwoDictionaries {

    static String str1;
    static String str2;

    static void checkAnagramFunction(String s1, String s2) {
        if (s1 == null || s2 == null) {
            System.out.println("Строка пустая");
            return;
        }
        Map<Character, Integer> mapStr1 = new HashMap<>();
        Map<Character, Integer> mapStr2 = new HashMap<>();

        for (Character ch : s1.toCharArray()) {
            mapStr1.put(ch, mapStr1.getOrDefault(ch, 0) + 1);
        }
        for (Character ch : s2.toCharArray()) {
            mapStr2.put(ch, mapStr2.getOrDefault(ch, 0) + 1);
        }

        boolean result = mapStr1.entrySet().equals(mapStr2.entrySet());
        System.out.println(result);


        for (Map.Entry<Character, Integer> entry : mapStr1.entrySet()) {
            char n = entry.getKey();
            Integer i = entry.getValue();
            if (!mapStr2.containsKey(n)) {
                System.out.println("Ключ не совпадает " + n);

                if (!mapStr2.get(n).equals(mapStr1.get(n))) {
                    System.out.println("Значение не совпало " + mapStr1.get(n) + " ключ " + n);
                }
            }
        }
    }

    static void main(String[] args) {
        str1 = "Vasia";
        str2 = "Vasal";
        FreeTask.Collection.CheckAnagram.checkAnagramFunction(str1, str2);
    }
}


