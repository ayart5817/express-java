package FreeTask.Collection;

import java.util.*;

public class SortNumbReminder47 {
    static List<Integer> numberList = new ArrayList<>(List.of(-10,1,2,3,4,5,6,9,8,7,5,6,5,5,5));

    static void sortNumber(List<Integer> l, int d) {
        Map<Integer, List<Integer>> sortedMap = new TreeMap<>();
        for (Integer n: l) {
           int x =  n % d;
           sortedMap.computeIfAbsent(x, k ->new ArrayList<Integer>()).add(n);
        }
        for(Map.Entry<Integer, List<Integer>> entry : sortedMap.entrySet()) {
            System.out.println("Результат " + entry.getKey()+ " — " + entry.getValue());
        }
    }

    static void main(String[] args) {
        sortNumber(numberList, 4);
    }
}
