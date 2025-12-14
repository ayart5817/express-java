package FreeTask.Class_Object;

import java.util.ArrayList;
import java.util.List;

public class Task {
    String description;
    String priority;

    public Task(String description, String priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "[" + priority.toUpperCase() + "] " + description;
    }

    static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Сделать отчет", "HIGH"));
        tasks.add(new Task("Отправить письмо", "MEDIUM"));
        tasks.add(new Task("Проверить почту", "LOW"));

        // Вывод всех задач
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}