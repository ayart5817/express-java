package practice.programming.principles;

import java.util.Arrays;

/**
 * Исходный код:
 * public class MathOperations {
 * public int add(int a, int b) {
 * return a + b;
 * }
 * public int addThreeNumbers(int a, int b, int c) {
 * return a + b + c;
 * }
 * public int addFourNumbers(int a, int b, int c, int d) {
 * return a + b + c + d;
 * }
 * }
 **/

public class MathOperationss {
    public int add(int... nums) {
        int result = Arrays.stream(nums).sum();
        return result;
    }

    static void main(String[] args) {
        MathOperationss mathOperationss = new MathOperationss();
        System.out.println(mathOperationss.add(1, 2, 3));
    }

}
