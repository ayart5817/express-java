package FreeTask.other;

import java.util.HashMap;
import java.util.Map;

/**
 *  счастливое число
 *  Счастливое число которое x встречается x раз
 * [1233] - 3 - максимальное значение число
 * [112244] - 4
 * [] - пусто
 * словарь считаем пересечение
 * сортируем max
 * находим макс в массиве
 */
public class LuckNumber {
    int[] arr;

    public static void findLuckyNumber(int[] arr, int x) {
        if (arr.length == 0) {
            System.out.println("Массив пуст");
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Integer i : arr) {
            countMap.put(i, countMap.getOrDefault(i, 0) + 1);
        }
        int maxLucky = -1;

        //проверяем совпадение

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();
            if (number == frequency) {
                if (maxLucky < number) {
                 maxLucky = number;
                }
             }
        }
        System.out.println(maxLucky);
    }

    static void main() {
        int[] arr = {1,2,3,3,3,4,5};
        LuckNumber.findLuckyNumber(arr, 1);
        int[] arr1 = {1,1,2,2,4,4,4,4};
        LuckNumber.findLuckyNumber(arr1, 1);
        int[] arr2 = {};
        LuckNumber.findLuckyNumber(arr2, 1);
    }


}
