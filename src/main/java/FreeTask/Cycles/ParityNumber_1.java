package FreeTask.Cycles;

public class ParityNumber_1 {
    public static void checkParity(int a) {

        if (a % 2 == 0) {
            System.out.println("Число " + a + " четное");
        } else {
            System.out.println("Число " + a + " не четное");
        }
    }

    static void main() {
        ParityNumber_1.checkParity(10);
        ParityNumber_1.checkParity(11);
    }
}
