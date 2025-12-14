package multithreading_practic_11;

public class Task_2 {
    public static void main(String[] args) {

        Runnable threadA = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 5; i++) {
                    System.out.println("A");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Поток 1 завершен");
            }
        };

        Runnable threadB = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 5; i++) {
                    System.out.println("B");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Итерация " + i);
                }
            }

        };
        Thread thread1 = new Thread(threadA);
        Thread thread2 = new Thread(threadB);
        thread1.start();
        thread2.start();
    }
}
