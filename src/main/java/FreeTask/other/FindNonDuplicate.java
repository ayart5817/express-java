package FreeTask.other;

import java.util.HashMap;
import java.util.Map;

/** находим не дублирующее число
 * Если дублирующего числа нет верни -1
 *
 *
 */


public class FindNonDuplicate {
    static int findNonDup(int[] numbers) {
        if (numbers == null || numbers.length == 0);
        // Шаг 1: Считаем частоты
        Map<Integer, Integer> mapNumbers = new HashMap<>();

        for (int num : numbers) {
            mapNumbers.put(num, mapNumbers.getOrDefault(num, 0) + 1);
        }
        //шаг 2
        for (int num : numbers) {
            if (mapNumbers.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
    int[] numbers = {1, 2, 1, 2, 3}; //1
    int[] numbers2 = {1, 2, 1, 2};//-1
    int[] numbers3 = {4};//4
    int[] numbers4 = {4, 4};//-1

        System.out.println(findNonDup(numbers));
        System.out.println(findNonDup(numbers2));
        System.out.println(findNonDup(numbers3));
        System.out.println(findNonDup(numbers4));



    }

}
