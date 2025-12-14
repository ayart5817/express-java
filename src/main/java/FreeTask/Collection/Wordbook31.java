package FreeTask.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wordbook31 {
    static public Map<Integer, List<Integer>> myMap = new  HashMap<>();

    static void add(Integer key, Integer value) {
        myMap.computeIfAbsent(key, v-> new ArrayList<>()).add(value);
        System.out.println("Добавлен эл-т " + key);
    }
    static void remove(Integer kay) {
        myMap.remove(kay);
        System.out.println("Удален " + kay);
    }
    static void get(Integer key) {
        System.out.println(myMap.get(key));
    }

    static void main(String[] args) {
        Wordbook31.add(1,1);
        Wordbook31.add(2,1);
        Wordbook31.add(3,1);
        Wordbook31.add(3,2);
        Wordbook31.add(3,3);
        System.out.println(myMap.entrySet());

        Wordbook31.remove(3);
        Wordbook31.remove(1);
        Wordbook31.get(2);
        Wordbook31.get(3);
        System.out.println(myMap.entrySet());

    }
}

