package FreeTask.Collection;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {
    static Map<Character, Integer> myCharMap = new HashMap<>();


    public static void countCharStr(String str) {
        char[] arrayChar = str.toCharArray();
        for (char ch : arrayChar) {
            if (myCharMap.containsKey(ch)) {
                myCharMap.put(ch, (myCharMap.get(ch) + 1));
            } else {
                myCharMap.put(ch, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : myCharMap.entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }
    }


    static void main(String[] args) {
        CharacterFrequency.countCharStr("яблоко груша яблоко апельсин груша яблоко");
    }
}
