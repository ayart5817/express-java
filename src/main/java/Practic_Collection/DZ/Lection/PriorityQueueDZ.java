package Practic_Collection.DZ.Lection;

import java.util.PriorityQueue;

public class PriorityQueueDZ {
    public PriorityQueue<Integer> myPriorityQueue = new PriorityQueue<>();

    public void addAuto() {
        myPriorityQueue.add(50);
        myPriorityQueue.add(51);
        myPriorityQueue.add(22);
        myPriorityQueue.add(1);
        myPriorityQueue.add(0);
    }
    public void printMyPriorityQueue() {
        for (int n : myPriorityQueue) {
            System.out.println("Значение —" + n);
        }
    }
}
