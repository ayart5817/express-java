package Task;

/**
 *  дана строка "мама купила 5 яблок 10 груш" найти
 *  найти все число окружен пробелами
 *
 *
 *
 */

public class SergeMoc {


    static String getNumber(String str) {
        return str.replaceAll("[^0-9]", " ");
    }

    public static void main(String[] args) {
        System.out.println(getNumber("мама купила 5 яблок 10груш"));
    }
}
