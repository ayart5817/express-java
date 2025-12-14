package FreeTask.other;

import java.util.*;


/** вывести уникальные слова из массива
 * string = banana, apple, java, Banana
 * string2 = Banana, potato, kiwi, book
 *
 *
 */




public class getUniqueValueFromTwoString {


    public static String [] getuniquevaluefromtwostring(String str1, String str2) {
        if(str1.length() < 3 || str2.length() < 3 || str1.length() > 30 || str2.length() > 30)
            throw new IllegalArgumentException("Строка не более 30 и не менее 3-х символов ");
        String simStr = str1 + ", " + str2;
        String [] words = simStr.toString().split(", ");

        Map<String, Integer> frequencyOfWords = new HashMap<>();
        for (String word : words) {
            if (!frequencyOfWords.containsKey(word)) {
                frequencyOfWords.put(word, 1);
            } else {
                frequencyOfWords.put(word, frequencyOfWords.get(word) + 1);
            }
        }
        List<String> resultList = new LinkedList<>();
        for (String word:frequencyOfWords.keySet()) {
            if (frequencyOfWords.get(word) == 1) {
                resultList.add(word);
            }
        }

        return resultList.toArray(new String[0]);
    }


    public static void main(String[] args) {
    String string = "banana, apple, java, Banana";
    String string2 = "Banana, potato, kiwi, book";
        System.out.println(Arrays.toString(getuniquevaluefromtwostring(string, string2)));



    }
}
