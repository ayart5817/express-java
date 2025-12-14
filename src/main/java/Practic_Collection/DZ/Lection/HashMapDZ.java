package Practic_Collection.DZ.Lection;

import java.util.HashMap;

public class HashMapDZ {
    public static HashMap<String, Integer> myHashMap = new HashMap<>();

    public static void addAuto() {
        myHashMap.put("Антон", 1865486685);
        myHashMap.put("Андрей", 177453453);
        myHashMap.put("Алеша", 16453453);
        myHashMap.put("Александр", 20435453);
        myHashMap.put("Александра", 20453453);
        myHashMap.put("Александра", 204534534);
    }

    public void printHashMap() {
         {
            System.out.println(myHashMap);
        }
    }
    public void findKeyHashMap(String name) {
       if (myHashMap.containsKey(name)) {
           System.out.println("Совпадение по имени " + myHashMap.containsKey(name) + " для " + name);
       } else {
           System.out.println("Совпадений нет с именем " + name);
       }
    }
    public void findValueHashMap(int name) {
        if (myHashMap.containsValue(name)) {
            System.out.println("Совпадение по возрасту " + myHashMap.containsValue(name) + " для " + name);
        } else {
            System.out.println("Совпадений нет по возрасту" + name);
        }
    }
    public HashMap<String, Integer> getMyHashMap() {
        HashMapDZ.addAuto();
        return myHashMap;
    }

}
