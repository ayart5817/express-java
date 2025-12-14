package MOK;

public class mocOlga {
    /**
     * Имплементировать метод стар вив
     * Передается строка и некоторый суффикс(префикс) нужно вернуть тру фолс
     * <p>
     * Создать утилитарный клас? или внутри?
     */

    public static boolean checkPrefix(String str, String syf) {
       if (str == null || syf == null || str.trim().isEmpty() || syf.trim().isEmpty()) {
           System.out.println("Суфикс и строка не может быть пустой ");
           return false;
       }
       if (str.length() < syf.length()) {
           System.out.println("Строка меньше слова");
           return false;
       }
        char[] charStr = str.trim().toCharArray();
        char[] charSyf = syf.trim().toCharArray();
        boolean b = true;
        int i = 0;
        for (char x : charSyf) {
            char c = charStr[i];
            if (c == x) {
                i++;
            } else {
                b = false;
                break;
            }

        }

        System.out.println("строка " + str + " и префикс " + syf + " – " + b);
        return b;
    }

    static void main(String[] args) {
        checkPrefix("Маша", "Маш");
        checkPrefix("Маша", "Маr");
        checkPrefix("Маша", "");
        checkPrefix(null, "123");
    }
}

