package FreeTask.Collection;


import java.util.*;
import java.util.List;

public class TopNLongestWords46 {
    static Map<Integer, String> countWords = new TreeMap<>();
    static String[] strings = {"adad", "ad", "table", "afoiaf"};
    static void sortWors(String[] string, int n) {

        for (String word : string) {
           int l = word.length();
           countWords.put(l,word);
        }
        int i = 0;
        for (Map.Entry<Integer, String> entry : countWords.entrySet()) {

            i++;
            if ( i > countWords.size() - n) {
                System.out.println(entry.getValue() + " — " +entry.getKey());
            }
        }

    }
    static void countWord2(String[] string, int n) {
        Arrays.stream(string).sorted(Comparator.comparingInt(String :: length).reversed().thenComparing(String::compareTo))
                .limit(n)
                .forEach(words-> System.out.println(words+ " — " + words.length()));

    };
    static void countSorted3(String[] string, int n) {
       List<String> myList = new ArrayList<>(List.of(string));
        System.out.println(myList);
        myList.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(myList.stream().limit(n).toList());


    }

    static void main(String[] args) {
        sortWors(strings, 2);
        countWord2(strings, 2);
        countSorted3(strings, 3);

    }
}
