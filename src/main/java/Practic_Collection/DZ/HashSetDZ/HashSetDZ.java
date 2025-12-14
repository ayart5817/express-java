package Practic_Collection.DZ.HashSetDZ;

import java.util.HashSet;

public class HashSetDZ {
    private HashSet<Integer> myHashSet = new HashSet<>();

    public void addAuto() {
        myHashSet.add(33);
        myHashSet.add(34);
        myHashSet.add(35);
        myHashSet.add(36);
        myHashSet.add(37);
        myHashSet.add(37);

    }

    public void printMyHashSet() {
        for (Integer number : myHashSet ) {
            System.out.println("Значение: " + number );
        }
    }
    public void checkInt(Integer number) {

            if (myHashSet.contains(number)) {
                System.out.println("Значение " + number + " есть в множестве");
            }
            else
                System.out.println("Значение " + number + " отсутствует в множестве");
        }


}
