package FreeTask.static_final;


/**
 * Утилитный класс для работы со строками.
 * Нельзя создавать экземпляры и наследоваться.
 */
public final class StringUtils {

    // Приватный конструктор запрещает создание объектов
    private StringUtils() {
        // Защита от вызова даже внутри класса
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Проверяет, является ли строка палиндромом (игнорируя регистр и пробелы).
     */
    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0;
        int right = cleaned.length() - 1;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Обрезает строку до указанной длины и добавляет "..." при усечении.
     */
    public static String truncate(String str, int maxLength) {
        if (str == null) return null;
        if (str.length() <= maxLength) return str;
        return str.substring(0, Math.max(0, maxLength - 3)) + "...";
    }

    /**
     * Безопасная проверка на пустоту или null.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}