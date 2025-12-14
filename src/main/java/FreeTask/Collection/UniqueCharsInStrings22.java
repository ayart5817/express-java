package FreeTask.Collection;

import java.util.*;

public class UniqueCharsInStrings22 {
    static List<String> myList = Arrays.asList("avbb", "avg", "nko");

    public static void checkCharacter() {
        Map<Character, Integer> chCount = new HashMap<>();
        Set<Character> chSet = new HashSet<>();
        for (String line : myList) {

            for (char ch : line.toCharArray()) {
                chSet.add(ch);
            }
            for (char ch : chSet) {
                chCount.put(ch, chCount.getOrDefault(ch, 0) + 1);
            }
        }
        List<Character> result = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : chCount.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        System.out.println(result);
    }

    static void main(String[] args) {
        UniqueCharsInStrings22.checkCharacter();
    }
}
