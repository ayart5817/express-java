package Practic_Collection.DZ.Lection;

import java.util.ArrayDeque;

public class ArrayDequeDZ {
    ArrayDeque<String> myArrayDeque = new ArrayDeque<>();
    public void addAuto() {
        myArrayDeque.add("99");
        myArrayDeque.add("91");
        myArrayDeque.add("92");
        myArrayDeque.add("99");
        myArrayDeque.add("93");
        myArrayDeque.add("94");

    }
    public void printMyArrayDeque() {
        for (String n : myArrayDeque) {
            System.out.println(n);
        }
    }
    public void addMyArrayDeque(String n) {
        System.out.println("Элемент добавлен в конец очереди");
        myArrayDeque.addLast(n);
    }
    public  void dellMyArrayDeque() {
        System.out.println("Элемент удален из конца очереди " + myArrayDeque.poll());

    }
}
