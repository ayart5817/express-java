package multithreading_practic_11;

public class Task_4 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(()-> {
           for (int i = 0; i < 1000; i++) {
               counter.increment();
           }
        });
    Thread thread2 = new Thread(()-> {
        for (int i= 0; i < 1000; i++) {
            counter.increment();
        }
    });

    thread2.start();
    thread1.start();


    thread1.join();
    thread2.join();

    System.out.println("Результат " + counter.getCounter());


    }

}
