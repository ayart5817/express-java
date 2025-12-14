package FreeTask.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupStudentsSimple {
    static Map<String, Integer> students = new HashMap<>();

    static {
        students.put("Анна", 2);
        students.put("Борис", 1);
        students.put("Вера", 2);
        students.put("Глеб", 3);
    }

    static void GropedStudent() {
        Map<Integer, List<String>> group = new HashMap<>();
        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            int course = entry.getValue();
            String name = entry.getKey();
            if (!group.containsKey(course)) {
                group.put(course, new ArrayList<>());
            }
            group.get(course).add(name);

        }
        for (Map.Entry<Integer, List<String>> entry : group.entrySet()) {
            System.out.println(entry.getKey() + " — курс. Студент: " + entry.getValue());
        }
    }

    static void main(String[] args) {
        GropedStudent();
    }
}
