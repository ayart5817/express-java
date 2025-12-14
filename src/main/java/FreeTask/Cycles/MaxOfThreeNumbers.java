package FreeTask.Cycles;

public class MaxOfThreeNumbers {
    public static void maxOfThreeNumbersMethod(int a, int b, int c) {
       int max = a;
       if(max < b) {max = b;};
       if(max < c) {max = c;}
        System.out.println("макс число = " +max);
    }


    static public void main(String[] args) {
        maxOfThreeNumbersMethod(1, 2, 3);
        maxOfThreeNumbersMethod(10, 1, 2);
        maxOfThreeNumbersMethod(29, 30, 10);
        maxOfThreeNumbersMethod(30, 30, 30);
    }
}
