package FreeTask.JenericAndException;

import java.util.ArrayList;
import java.util.List;

public class PrintArray {
    static void print(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    static void main(String[] args) {
                List<String> l1 = new ArrayList<>(List.of("one", "Apple", "string"));
                List<Integer> l2 = new ArrayList<>(List.of(1,2,3,6,5,5));
                List<Integer> l3 = null;
                print(l1);
                print(l2);
                print(l3);
    }
}
