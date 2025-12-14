package FreeTask.other;

import java.util.Locale;

/**
 * "Привет Мир!" ->f  палиндром "Гаг гаг" = -t "ГараГ"-t
 *  1) понять задачу
 *  2) разобраться с примерами
 *  3) найти логический алгоритм
 *  4) протестировать код
 *  5) перенести алгоритм
 *  6 протестировать свой же код
 */

public class Palindrome {

    // Убрать  лишнее, привести к единому регистру Сравнить строки

    public static boolean isCheckIfPalindrome(String str) {

        String clearString = str.replaceAll("[^A-Za-z]", "");
        System.out.println(clearString);

        String lowerString = clearString.toLowerCase(Locale.ROOT);
        System.out.println(lowerString);

      return lowerString.contentEquals(new StringBuilder(lowerString).reverse());
    }

    static void main() {
        String str = "Gag Gag";
        System.out.println(Palindrome.isCheckIfPalindrome(str));
        String str2 = "Gag fag";
        System.out.println(Palindrome.isCheckIfPalindrome(str2));
    }

}
