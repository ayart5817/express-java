package FreeTask.Collection;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AlphabetCheck59 {
    static final Set<Character> ALPHABET = "qwertyuiopasdfghjklzxcvbnm".chars().mapToObj(c-> (char) c).collect(Collectors.toSet());

    static void checkAlphabet(String  str) {
       Set<Character> alp = new HashSet<>( ALPHABET);
        char[] s = str.toCharArray();
        for (char ch : s) {
            alp.remove(ch);

        }
        System.out.println(alp.isEmpty()?"Есть dct" : "нет совпадений по "+alp.toString());
    }

    static void main(String[] args) {
        checkAlphabet("qwertyuioplkjhgfdsazxcvbnm");
        checkAlphabet("qwertyuioplkjhgfd");
    }
}
