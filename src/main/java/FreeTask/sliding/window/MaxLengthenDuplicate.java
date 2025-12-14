package FreeTask.sliding.window;

import java.util.HashSet;
import java.util.Set;

public class MaxLengthenDuplicate {
    static void maxLengthSubstring(String str) {
        char ch[] = str.replaceAll("[^A-Za-z]", "").toCharArray();
        int left = 0;

        Set<Character> seen = new HashSet<>();
        int maxlength = 0;
        for (int right=0; ch.length > right; right++) {
            char c = ch[right];
            while (seen.contains(c)) {
                seen.remove(ch[left]);
                left++;
            }
            seen.add(c);
            maxlength = Math.max(maxlength,right-left+1);
        }
        System.out.println(maxlength);

    }


    static void main(String[] args) {

        maxLengthSubstring("abcabcbb"); //abc 3
        maxLengthSubstring("abcafghtyfddd"); //bcafghty 8
    }
}