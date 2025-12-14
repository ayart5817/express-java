package FreeTask.Collection;

import java.util.*;

public class MapNameAge3 {
static     Map<String,Integer> nameAgeMap = new HashMap<>(Map.of("Alex", 30, "Andrei", 14));
static LinkedHashSet<String> Guest = new LinkedHashSet<>(Set.of("Alex",  "Andrei",  "MAX",  "bob"));
static void findNameFoAge(String name) {
    System.out.println(nameAgeMap.get(name));
}

    static void main(String[] args) {
        MapNameAge3.findNameFoAge("Alex");
        System.out.println(nameAgeMap);
        for (String name: Guest){
            System.out.println(name);
        }
    }
}
