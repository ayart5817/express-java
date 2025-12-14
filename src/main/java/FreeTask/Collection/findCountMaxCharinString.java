package FreeTask.Collection;

import java.util.HashMap;
import java.util.Map;

public class findCountMaxCharinString {


    static char find(String s) {

        s = s.replaceAll("[^a-z]", "");
        if (s.isEmpty()) return '?';

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }


        char best = 'z';
        int max = 0;

        for (char c : freq.keySet()) {
            int count = freq.get(c);
            if (count > max || (count == max && c < best)) {
                max = count;
                best = c;
            }
        }
        System.out.print(max + " — ");
        return best;
    }

    public static void main(String[] args) {
        System.out.println(find("abracadabra")); // a
        System.out.println(find("bbaa"));         // a
        System.out.println(find("xyz"));          // x
        System.out.println(find(""));             // ?
    }
}



