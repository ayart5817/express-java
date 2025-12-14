package Practic_Collection.DZ.Lection;

import java.util.TreeSet;

public class TreeMapDZ {
    public TreeSet<String> myTreeSet = new TreeSet<>();


    public void addAuto() {
        myTreeSet.add("Артём");
        myTreeSet.add("Ян");
        myTreeSet.add("Маша");
        myTreeSet.add("Владик");
        myTreeSet.add("Артемон");

    }

    public void printMyTreeSet() {
        for (String name : myTreeSet) {
            System.out.println(name);
        }
    }


    public void minMaxMyTreeSet() {
        System.out.println("Первый элемент "  + myTreeSet.first());
        System.out.println("Последний элемент " + myTreeSet.last());

    }
}
