package Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 1) создать коллекцию чар
 * 2) написать метод который добавляет в коллекцию данные
 * 3) наполнить список 10 символами латинского алфавита
 * 4) Написать метод поиска символа в списке (без учета регистра)
 *
 *
 *
 */

public class Pavel {

    static List<Character> charList = new ArrayList<>();

    static void addRandomCharAtList(List<Character> charList) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            boolean flag = random.nextBoolean();
            if (flag) {
                char ch = (char) (random.nextInt(26) + 'A');

                charList.add(ch);
            } else {
                char ch = (char) (random.nextInt(26) + 'a');
                charList.add(ch);
            }
        }
    }

    static boolean findSimbolInCharArray(Character ch) {
        Character lowerCh = Character.toLowerCase(ch);
        for (char c : charList) {
            if( Character.toLowerCase(c) == ch) {
                return true;
            }
        } return false;

    }

    public static void main(String[] args) {
        addRandomCharAtList(charList);
        System.out.println(charList);
        System.out.println(findSimbolInCharArray('a'));
    }

}
