package FreeTask.other;

import java.util.HashMap;
import java.util.Map;

public class FirstNotConditionString {
    static String n = "PPFFghYN";

    /**
     * Строка нужно найти 1вый не повторяющийся символ в строке
     * Вывести его индекс
     * строка = "PPFFghYN" - g
     * "G" - g
     * "JEKRASSEL" - j
     * <p>
     * Приводим строку в надлежавший вид (пробелыЮ lower case)
     * строку перевести ив Массив
     * перебираем буквы ведем словарь
     * Далее перебираем словарь пока не найдем символ со значением 1
     */


    public static void findFirsNotCondition(String n) {
        String cleaned = n.trim().toLowerCase()
                .replaceAll("[^A-Za-z0-9]]", "");
        if (cleaned == null|| cleaned.isEmpty()) {
            System.out.println("Массив пуст ");
            return;
        }

        Map<Character, Integer> nMap = new HashMap<>();
        for (char c : cleaned.toCharArray()) {
            nMap.put(c, nMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < cleaned.length(); i++) {
            char c = cleaned.charAt(i);
            if (nMap.get(c) == 1) {
                System.out.println("искомый индекс " + i);
                return;
            }
        }
        System.out.println("Элемента нет ");

    }
    // frequent.put(c, frequent.getOrDefault(c, 0)+ 1);

    public static void main(String[] arg) {
        String n = "PPFFghYN";
        FirstNotConditionString.findFirsNotCondition(n);
        String n1 = "Gg";
        FirstNotConditionString.findFirsNotCondition(n1);
        String n2 = "";
        FirstNotConditionString.findFirsNotCondition(n2);
        String n3 = "Null";
        FirstNotConditionString.findFirsNotCondition(n3);
        String n4 = "Javac";
        FirstNotConditionString.findFirsNotCondition(n4);
        String n5 = "019328701901932870194";
        FirstNotConditionString.findFirsNotCondition(n5);
    }
}




