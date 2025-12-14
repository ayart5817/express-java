package FreeTask.Collection;
import java.util.List;

import java.util.*;
import java.util.stream.Collectors;

public class StudentSorted {

    static     ArrayList<String> student = new ArrayList<>(List.of("Максим","Андрей", "Данил", "Виталик", "Мария", "Ёлка"));
    static public void sorted() {
        System.out.println(student);
        Collections.sort(student);
        System.out.println(student);
    }
    static public void sortedComparator() {
        ArrayList<String> student2 =  new ArrayList<>(List.of("Максим","Андрей", "Данил", "Виталик", "Мария", "Ёлка"));
        System.out.println(student2);
        student2.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(student2);
    }
    public static void streamSortMap() {
        List<String> students = List.of("Максим", "Андрей", "Мария");

        TreeMap<String, Boolean> map = students.stream()
                .collect(Collectors.toMap(
                        name -> name,           // ключ
                        name -> true,           // значение
                        (oldVal, newVal) -> oldVal, // обработка дубликатов
                        TreeMap::new            // тип мапы
                ));

        System.out.println(map); // {Андрей=true, Максим=true, Мария=true}
    }

    public static void main(String[] args) {
        StudentSorted.sorted();
        StudentSorted.sortedComparator();
        StudentSorted.streamSortMap();
    }
}
