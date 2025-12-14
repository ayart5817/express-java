package FreeTask.Collection;

import java.util.HashMap;
import java.util.Map;

public class CheckAnagram {
    static  String str1;
    static String str2;
    static void checkAnagramFunction(String s1, String s2) {
        if (s1 == null || s2 == null) {
            System.out.println("Строка пустая");
            return;
        }
        Map<Character, Integer> mapStr1 = new HashMap<>();
        Map<Character, Integer> mapStr2 = new HashMap<>();

        for (Character ch : s1.toCharArray()) {
            mapStr1.put(ch, mapStr1.getOrDefault(ch,0) +1);
        }
        for (Character ch : s2.toCharArray()) {
            mapStr2.put(ch, mapStr2.getOrDefault(ch,0) +1);
        }

        boolean result = mapStr1.entrySet().equals(mapStr2.entrySet());
        System.out.println(result);
    }

    static void main(String[] args) {
        str1 = ""; str2 = "";
        CheckAnagram.checkAnagramFunction(str1, str2);
    }
}
