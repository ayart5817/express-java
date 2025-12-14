package ComplexTask2.Task6;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskService<T> {
    private final Map<T, Task<T>> tasks = new ConcurrentHashMap<>();

    public boolean addTask(Task<T> task) {
        if (task == null) {
            throw new IllegalArgumentException("Задача не может быть Null");
        }

        return tasks.putIfAbsent(task.getId(), task) == null;
    }

    public void cancelTask(T id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть Null");
        }
        tasks.remove(id);
    }

    public List<Task<T>> sortedTaskByDateAsk() {
        return tasks.values().stream()
                .sorted((t1, t2) -> t1.getDate().compareTo(t2.getDate()))
                .toList();
    }

    public List<Task<T>> sortedTaskByDateDesc() {
        return tasks.values().stream()
                .sorted((t1,t2) -> t2.getDate().compareTo(t1.getDate()))
                .toList();
    }

    public List<Task<T>> sortedAllTaskByStatus() {
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .toList();
    }

    public List<Task<T>> sortedAllTaskByPriority() {
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .toList();
    }


    public Task<T> getTask(T id) {
        return tasks.get(id);
    }

    public List<Task<T>> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public boolean containsTask(T id) {
        return tasks.containsKey(id);
    }

    public int getTaskCount() {
        return tasks.size();
    }
}