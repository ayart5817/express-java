package Practic_Collection.DZ.TreeSet;

import java.util.TreeSet;

public class TreeSetDZ  {
    public TreeSet<Integer> myTreeSetInt = new TreeSet<>();


    public void addAuto() {
        myTreeSetInt.add(13);
        myTreeSetInt.add(14);
        myTreeSetInt.add(15);
        myTreeSetInt.add(16);
        myTreeSetInt.add(17);
        myTreeSetInt.add(17);
        myTreeSetInt.add(10);



    }

    public void printMyTreeSet() {
        for (Integer number : myTreeSetInt) {
            System.out.println(number);
        }

    }
    public void findNearest(Integer n) {
        Integer lower = myTreeSetInt.lower(n);
        Integer higher = myTreeSetInt.higher(n);
        System.out.println("Искомое число "+ n + " ближайшее меньшее число —" + lower + " ближайшее большее число —" + higher );

    }


}
