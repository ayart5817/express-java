package FreeTask.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicate {
    public static List<Integer> myList = new ArrayList<>();

    public static void remDuplicate(List<Integer> myList) {
        List<Integer> result = (List<Integer>) myList.stream().distinct().toList();
        for (Integer n : result) {
            System.out.println(n);
        }
    }

    static void main(String[] args) {
        myList = Arrays.asList(1, 3, 4, 6, 9, 2, 2, 3, 4, 5, 6, 78, 9, 9);
        RemoveDuplicate.remDuplicate(myList);
    }
}
