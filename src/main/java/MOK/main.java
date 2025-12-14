package MOK;

/**
 *  Метод который проверяет 2 строки являються ли анаграммой
 *  str1 = aab
 *  str2 = baa
 *   Обработать, не учитываем регистр
 *   на пустую строку отдать (Строка должна быть не пустой)
 *
 *   Проверка на пустую строку
 *   взять 2 строки - привести к одному ргистру, обрезать пробелы,
 *   Сравнить символы у двух строк их у 2-х строк - вернуть true либо false toCharArray
 *
 */


class Anagram  {

    static public boolean anagram(String str1, String str2) {

        if (str1 == "" || str2 == "")  {
            return false;
        };
        assert str1 != null;
        char[] correctStr1 = str1.toLowerCase().replaceAll("[^A-Za-z0-9]","").toCharArray();
        assert str2 != null;
        char[] correctStr2 = str2.toLowerCase().replaceAll("[^A-Za-z0-9]","").toCharArray();

        java.util.Arrays.sort(correctStr1);
        java.util.Arrays.sort(correctStr2);

        return java.util.Arrays.equals(correctStr1, correctStr2);

    }

    static void main() {
        String str1 = "099";
        String str2 = " 099";
        System.out.println(Anagram.anagram(str1, str2));

    }
}
