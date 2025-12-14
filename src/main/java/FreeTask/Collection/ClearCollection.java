package FreeTask.Collection;

import java.util.ArrayList;
import java.util.List;

public class ClearCollection {
    static List<Integer> myList = new ArrayList<>(List.of(1,2,3,4,5,6,9,8,7,88,55,64,846));
    public static void clearList(Integer n) {
        for (int i = myList.size()-1; i >= 0; i--) {
             Integer s = myList.get(i);
            if (!s.equals(n)) {
                myList.remove(n);
            }
        }
        System.out.println("Удалены элементы " +n + "  " +myList.toString());
    }

    public static void clearList2(Integer n) {
       myList.removeIf(s -> s.equals(n));
        System.out.println("Удалены элементы " +n + "  " +myList.toString());
    }


    static void main(String[] args) {
        ClearCollection.clearList2(846);
    }
}
