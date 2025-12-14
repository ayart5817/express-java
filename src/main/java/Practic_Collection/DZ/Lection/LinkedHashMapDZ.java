package Practic_Collection.DZ.Lection;

import java.util.LinkedHashMap;

public class LinkedHashMapDZ {
    public LinkedHashMap<String, Integer> myLinkedHashMap = new LinkedHashMap<>();

    public LinkedHashMap<String, Integer> getMyLinkedHashMap() {
        return myLinkedHashMap;
    }

    public void setMyLinkedHashMap(LinkedHashMap<String, Integer> myLinkedHashMap) {
        this.myLinkedHashMap = myLinkedHashMap;
    }


    public void addAuto() {
        HashMapDZ hashMapDZ = new HashMapDZ();
        HashMapDZ.addAuto();
        myLinkedHashMap.putAll(hashMapDZ.getMyHashMap());


    }
    public void printMyLinkedHashMap() {
        System.out.println(myLinkedHashMap);
    }
    public void addContact(String name, int number) {
        myLinkedHashMap.put(name, number);
        System.out.println("Контакт добавлен " + name + "—" +number );
    }
    public void getContact(String name) {
      if (myLinkedHashMap.containsKey(name)) {
          System.out.println(myLinkedHashMap.get(name) + " Телефон контакта " + name);
      }
      else { System.out.println( "Телефон не найден ");
      }
    }
    public void getContacts() {
        for (var entry : myLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + "—" + entry.getValue());
        }
    }

}


