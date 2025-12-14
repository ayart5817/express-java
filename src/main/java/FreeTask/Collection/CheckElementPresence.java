package FreeTask.Collection;

import java.util.ArrayList;
import java.util.List;

public class CheckElementPresence {
static ArrayList<String> myList = new ArrayList<>(List.of("яблоко", "груша", "яблоко", "апельсин", "груша", "яблоко"));
 public static void checkIndex(String str) {
     int index = myList.indexOf(str);
     if (index !=1) {
         System.out.println("Элемент под индексом " + index);
     } else {
         System.out.println("Элемент не найден");
     }
 }

    static void main(String[] args) {
        CheckElementPresence.checkIndex("яблоко");
        CheckElementPresence.checkIndex("апельсин");
    }
}
