package FreeTask.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterEven41 {
    static List<Integer> myList = Arrays.asList(1, 2, 3, 4, 4, 56, 7, 8, 9);
    static List<Integer> resultList = new ArrayList<>();

    static void filterList(List<Integer> list) {
        for (Integer n : list) {
            if (n % 2 == 0) {
                resultList.add(n);
            }
        }
        System.out.println(resultList);
    }

    static void main(String[] args) {
        filterList(myList);
    }
}
