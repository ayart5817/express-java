package FreeTask.Collection;
import java.util.List;
import java.util.*;

public class ReversSortedString {
    public static void reversSortedString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вводите строки, До СТОП");
        String n = "";
        List<String> myList = new ArrayList<>();
        while (true) {
            n = scanner.nextLine().trim();  // сразу убираем пробелы по краям
            if ("СТОП".equals(n)) {
                break;  // выходим из цикла, НЕ добавляя "СТОП"
            }
            if (!n.isEmpty()) {  // опционально: игнорировать пустые строки
                myList.add(n);
            }
        }

            myList.sort(Collections.reverseOrder());
        System.out.println(myList);

    }

    static void main(String[] args) {
        ReversSortedString.reversSortedString();
    }
}
