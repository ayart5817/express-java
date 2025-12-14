package Practic_Collection.DZ.Lection;

import java.util.LinkedList;

public class LinkedListDZ<S> {

    public LinkedList<String> mylinkedList = new LinkedList<>();

    public void addAuto() {
        mylinkedList.add("задача 1");
        mylinkedList.add("задача 2");
        mylinkedList.add("задача 3");
        mylinkedList.add("задача 4");
        mylinkedList.add("задача 5");
    }

    public void printLinkedList() {
        for (String name : mylinkedList) {
            System.out.println("задача: " + name);
        }
    }
    public void addTasks(String newTask) {
        mylinkedList.addLast(newTask);
        System.out.println("Задача добавлено — " + mylinkedList.getLast());
    }
    public void canselTask() {
        System.out.println("Задача удалена из очереди — " + mylinkedList.getFirst());
        mylinkedList.removeFirst();
    }

}
