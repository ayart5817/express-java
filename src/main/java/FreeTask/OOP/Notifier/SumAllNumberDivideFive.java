package FreeTask.OOP.Notifier;

import java.util.Arrays;

public class SumAllNumberDivideFive {
    /**
     * Сумма числес которые деляться на 5
     * <p>
     * 1,2,3,4,5 - сумма =5
     * 5,10,15,0 - сумма 30
     * 3 - 0
     * <p>
     * Обход чисел
     * проверка деления
     * sum = sum + n
     *
     * return
     *
     */


    static public int SumAllNumberDivideFiveMethod() {

        int[] numbers= {1,2,3,4,5,10};
        if (numbers.length == 0){
            System.out.println("Исходный массив пуст");
        }
        else {
            System.out.println(Arrays.toString(numbers));
        };
        int sum = 0;

        for (int n: numbers) {
            if (n % 5 == 0) {
                sum = sum + n;
            }
        }
        return sum;
    }

    static void main() {
        System.out.println(SumAllNumberDivideFiveMethod());
    }
}

