package ComplexTask2.Task6;

import java.time.LocalDate;
import java.util.Objects;

public class Task<T> {
    /**
     * сервис для управления задачами
     * добавлять, удалять и искать задачи по различным критериям
     * Каждая задача будет иметь уникальный идентификатор обобщённого типа, статус, приоритет и дату.
     * Task<T> TaskService<T>
     */

    private final T id;
    private TaskStatus status;
    private final Priority priority;
    private final LocalDate date;

    public Task(T id, Priority priority, LocalDate date, TaskStatus status) {
        if (id == null || priority == null || date == null || status == null) {
            throw new IllegalArgumentException("Все поля должны быть заполнены");
        }
        this.id = id;
        this.priority = priority;
        this.date = date;
        this.status = status;
    }

    public T getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task<?> task = (Task<?>) o;
        return Objects.equals(id, task.id) && status == task.status && priority == task.priority && Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, priority, date);
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", status=" + status +
                ", priority=" + priority +
                ", date=" + date +
                '}';
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
