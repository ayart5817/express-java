package MOCTask2.Pavel;

import java.util.Arrays;

/**
 * 2 - Строка на вход true если анаграмма
 *
 */

public class Main {

    static boolean checkAnagram(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty()) {
            throw new IllegalArgumentException("be not empty");
        }

        char[] charArray1 = str1.toLowerCase().replaceAll("[^A-Za-z]", "").toCharArray();
        char[] charArray2 = str2.toLowerCase().replaceAll("[^a-zA-Z]", "").toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);


        return Arrays.equals(charArray1, charArray2);

    }

    static void main(String[] args) {
        System.out.println(checkAnagram("abb", "bab"));
        System.out.println(checkAnagram("abb", "baa"));
        // System.out.println(checkAnagram("","baa"));
        System.out.println(checkAnagram("BA A", " baa"));

        Systems sys = new Systems();
        sys.addUser(new AnonimusUser("20.02.2066"));
        sys.addUser(new AuthenticateUser("20.02.2066", "40"));
        sys.addUser(new CorporateUser("22.04.1999","90","MicroCHak") );
        sys.getAllUserInfo();


    }

}
