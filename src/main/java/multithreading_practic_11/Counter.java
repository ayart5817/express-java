package multithreading_practic_11;

public class Counter {
    int  counter = 0;

    public int getCounter() {
        return counter;
    }

    public synchronized void increment() {
        counter++;

    }
}
