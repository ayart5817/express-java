package FreeTask.Collection;
import java.util.List;

import java.util.*;

public class TaskDayName {
    static Map<String, List<String>> myTask = new LinkedHashMap<>();
    static String[] newList = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};

    static {

        for (String day : newList) {
            myTask.put(day, new ArrayList<>());
        }

    }
    public static void addTask(String day, String task) {
        myTask.get(day).add(task);

    }

    static void main(String[] args) {
        TaskDayName.addTask("Понедельник", "Сделать домашку");
        TaskDayName.addTask("Понедельник", "Убраться");
        TaskDayName.addTask("Понедельник", "Приготовить поесть");
        TaskDayName.addTask("Вторник", "Учить язык");
        System.out.println(myTask.entrySet());
    }
}
