package MyWork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindeFirstUniqNamberInArray {
    static int[] myArray = {1, 2, 3, 1, 2};

    public static Integer countDouble(int[] numbers) {
        Map<Integer, Integer> counterNumber = new HashMap<>();
        for (int number : numbers) {
            counterNumber.put(number, counterNumber.getOrDefault(number, 0) + 1);
        }
        for (int num : numbers) {
            if (counterNumber.get(num) == 1) {
                return num;
            }
        }
        return null;
    } // Пример использования
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 4, 2, 4, 3, 5};
        System.out.println(countDouble(arr1)); // Вывод: 5

        int[] arr2 = {1, 1, 2, 2, 3, 3};
        System.out.println(countDouble(arr2)); // Вывод: null

        int[] arr3 = {7};
        System.out.println(countDouble(arr3)); // Вывод: 7
    }
}

