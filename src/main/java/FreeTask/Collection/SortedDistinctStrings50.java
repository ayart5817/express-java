package FreeTask.Collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedDistinctStrings50 {
    static List<String> myList= new ArrayList<>(List.of("Понедельник", "Вторник", "Вторник", "21", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"));

    static void sortDistinctMethod(List<String> l) {
      List<String> result = l.stream().distinct()
              .sorted(Comparator.comparing(String::length))
              .toList();
        System.out.println(result);
    }

    static void main(String[] args) {
        sortDistinctMethod(myList);
    }
}
