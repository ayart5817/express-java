package Practic_Collection.DZ.Lection;

import java.util.LinkedHashSet;

public class LinkedHashSetDZ {
    public LinkedHashSet<Integer> myLinkedHashSet = new LinkedHashSet<>();

    public void addAutoInt() {
        myLinkedHashSet.add(10);
        myLinkedHashSet.add(11);
        myLinkedHashSet.add(12);
        myLinkedHashSet.add(13);
        myLinkedHashSet.add(14);
        myLinkedHashSet.add(15);
        myLinkedHashSet.add(9);
        myLinkedHashSet.add(9);
    }

    public void printMyLHSInt() {
        for (Integer number : myLinkedHashSet) {
            System.out.println("Запись " + number);

        }
    }
    public LinkedHashSet<String> myLinkedHashSetString = new LinkedHashSet<>();

    public void addAutoString() {
        myLinkedHashSetString.add("10");
        myLinkedHashSetString.add("11");
        myLinkedHashSetString.add("12");
        myLinkedHashSetString.add("13");
        myLinkedHashSetString.add("14");
        myLinkedHashSetString.add("1577");
        myLinkedHashSetString.add("1577");
    }
    public void printMyLHSString() {
        for (String number : myLinkedHashSetString) {
            System.out.println("Запись " + number);

        }
    }
}
