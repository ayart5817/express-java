package FreeTask.other;

import java.util.List;

public class MokTask1 {
    /**
     * Массив чисел 1-10 посчитать количество отрицательных чисел
     */

    static List<Integer> myList1 = List.of(1, -10, -8, 0, 1, 3, 65, 8, 45);

    static void methodCount(List<Integer> myList) {
        if (myList.isEmpty()) {
            System.out.println("Массив не должен быть пустым");
            return;
        }
        int count = 0;
        for (Integer n : myList) {
            if (n <= 0) {
                count++;
            }

        }
        System.out.println("Количество положительных элементов " +count);
    }

    static void main(String[] args) {
        methodCount(myList1);
    }
}

