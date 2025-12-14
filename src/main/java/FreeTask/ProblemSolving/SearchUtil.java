package FreeTask.ProblemSolving;

import java.util.ArrayList;
import java.util.List;

public class SearchUtil {

   static public int findIndex(List<String> list, String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    static void main(String[] args) {
     List<String> l = new ArrayList<>(List.of("adadad", "adadad","wokgweog"));
        System.out.println(findIndex(l,"wokgweo"));
    }
}
