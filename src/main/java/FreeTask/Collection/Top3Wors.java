package FreeTask.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Top3Wors {
    static Map<String, Integer> myCharMap = new TreeMap<>();


    public static void countStr(String str) {
        String[] strArray = str.split(" ");
        for (String st : strArray) {
            if (myCharMap.containsKey(st)) {
                myCharMap.put(st, (myCharMap.get(st) + 1));
            } else {
                myCharMap.put(st, 1);
            }
        }
        //  Сортируем записи по частоте (по убыванию)
        System.out.println(myCharMap);
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(myCharMap.entrySet());
        System.out.println(sortedEntries);
        sortedEntries.sort((e1, e2) -> {
            int compare = e2.getValue().compareTo(e1.getValue()); // сначала по частоте (убывание)
            if (compare == 0) {
                return e1.getKey().compareTo(e2.getKey()); // затем по алфавиту (возрастание)
            }
            return compare;
        });


        int count = 0;


        for (Map.Entry<String, Integer> entry : sortedEntries) {

            if (count >= 3) {
                break;

            } else {
                count++;
                System.out.println(entry.getKey() + " — " + entry.getValue());
            }

        }
    }


    static void main(String[] args) {
        Top3Wors.countStr("яблоко груша яблоко апельсин груша яблоко банан гранат киви киви банан банан груша тыква тыква тыква тыква");
    }
}
