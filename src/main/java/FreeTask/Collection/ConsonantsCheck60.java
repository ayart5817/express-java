package FreeTask.Collection;

import java.util.*;
import java.util.stream.Collectors;

public class ConsonantsCheck60 {
    static final Set<Character> ALPHABET = "уеаоэяиюёыaeiouy".chars().mapToObj(c-> (char) c).collect(Collectors.toSet());

    static void checkAlphabet(String  str) {
       Set<Character> alp = new HashSet<>( ALPHABET);

        List<String> s = Arrays.stream(str.toLowerCase().split(" ")).toList();
        List<String> result = new ArrayList<>();
        for (String st : s) {
            boolean isContains = false;
            for (char ch: st.toCharArray()) {
                if (alp.contains(ch)) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {result.add(st);}
        }
        System.out.println(result.isEmpty()?"нет совпадений" : result.toString());
    }

    static void main(String[] args) {
        checkAlphabet("qwer tyui op lkjhgf dsa zxc vbnm");
        checkAlphabet("qw ertyu ioplkjhg fd");
    }
}
