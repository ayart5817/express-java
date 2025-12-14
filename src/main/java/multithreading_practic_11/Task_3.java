package multithreading_practic_11;

public class Task_3 {

    private static volatile boolean stop = false;
    static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread counterInfinite = new Thread(()-> {
            while(!stop) {
                counter++;
            }
            System.out.println("поток оставлен " + counter);

        });
        counterInfinite.start();
        counterInfinite.join();
        Thread.sleep(2000);

        stop = true;



    }
}
