package TaskServiceTest;

import ComplexTask2.Task6.Priority;
import ComplexTask2.Task6.Task;
import ComplexTask2.Task6.TaskService;
import ComplexTask2.Task6.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**    Low, Medium, High
 *     New, Open, Work, Close
 *      Протестируйте добавление, удаление и поиск задач,
 *      а также проверку фильтрации и сортировки. Убедитесь,
 *      что операции с задачами выполняются корректно.
 */

public class TaskServiceTest {

    TaskService<String> taskService = new TaskService<>();

    @Test
    public void addTaskCheckArgument() {
      Task<String> task1 = new Task<>("1198", Priority.Low, LocalDate.of(2025, 10, 19), TaskStatus.New);
      assertEquals("1198", task1.getId()); // проверка что ID<String> успешно добавлен
      assertEquals(Priority.Low,task1.getPriority()); // Priority.Low
      assertEquals(TaskStatus.New,task1.getStatus()); // Status,New
      assertEquals(LocalDate.of(2025,10,19),task1.getDate()); //Local.date 2025.10.19
    }

    @Test
    public void canselTask() {
        Task<Integer> task1 = new Task<>(11, Priority.Low, LocalDate.of(2025, 10, 19), TaskStatus.New);
        TaskService<Integer> taskService = new TaskService<>();
        assertTrue(taskService.addTask(task1));
        taskService.cancelTask(11);
        assertFalse(taskService.containsTask(11));
        assertEquals(0, taskService.getTaskCount());
    }

    @Test
    public void foundTask() {
        Task<Integer> task1 = new Task<>(11, Priority.High, LocalDate.of(2025, 10, 19), TaskStatus.New);
        Task<Integer> task2 = new Task<>(21, Priority.Low, LocalDate.of(2025, 10, 18), TaskStatus.Close);
        Task<Integer> task3 = new Task<>(9, Priority.Medium, LocalDate.of(2023, 10, 18), TaskStatus.Work);
        TaskService<Integer> taskService = new TaskService<>();
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);

        //Сортировка по Статусу New->Open->Work->Close
        List<Task<Integer>> sortedTasks =  taskService.sortedAllTaskByStatus();

       assertEquals(11, sortedTasks.getFirst().getId(), "проверяем что первый в списке таска с ID 11");
       assertEquals(TaskStatus.Close, sortedTasks.getLast().getStatus(), "проверяем что в списке последняя таска с статусом CLOSE");

        //Сортировка по приоритету Low->Medium->High
        List<Task<Integer>> sortedByPriority = taskService.sortedAllTaskByPriority();

        assertEquals(11, sortedByPriority.getLast().getId(), "Проверка сортировки по Priority, last id = 11");
        assertEquals(21, sortedByPriority.getFirst().getId(), "Проверка сортировки по Priority, first id = 21");

        //Сортировка по Дате DESC
        List<Task<Integer>> sortedByDateDesk = taskService.sortedTaskByDateDesc();
        assertEquals(LocalDate.of(2025, 10, 19), sortedByDateDesk.getFirst().getDate(), "Проверка сортировки по дате, DESC сравнение даты с 1 из списка");

        //Сортировка по Дате ASC
        List<Task<Integer>> sortedByDateAsc = taskService.sortedTaskByDateAsk();
        assertEquals(LocalDate.of(2023, 10, 18), sortedByDateAsc.getFirst().getDate(), "Проверка сортировки по дате, ASC сравнение даты с 1 из списка");

    }

    @Test
    public void getTask() {
        Task<Integer> task1 = new Task<>(11, Priority.High, LocalDate.of(2025, 10, 19), TaskStatus.New);
        Task<Integer> task2 = new Task<>(21, Priority.Low, LocalDate.of(2025, 10, 18), TaskStatus.Close);
        Task<Integer> task3 = new Task<>(9, Priority.Medium, LocalDate.of(2023, 10, 18), TaskStatus.Work);
        TaskService<Integer> taskService = new TaskService<>();
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);

        assertEquals(3, taskService.getAllTasks().size());
        Task<Integer> actualTask = taskService.getTask(11);
        assertEquals(11 ,actualTask.getId());
        assertNotNull(actualTask, "Проверка чт оне NuLL если на getTask");

    }


    @Test // проверка валидности полей конструктора
    public void negativeTestTaskService() {
        TaskService<Integer> taskService = new TaskService<>();

        assertThrows(IllegalArgumentException.class, () -> new Task<>(11, Priority.High, null, TaskStatus.New),"Null -> ID");
        assertThrows(IllegalArgumentException.class, ()->  new Task<>(21, null, LocalDate.of(2025, 10, 18), null));
        assertThrows(IllegalArgumentException.class, ()->  new Task<>(21, Priority.High, LocalDate.of(2025, 10, 18), null));
        assertThrows(IllegalArgumentException.class, ()-> new Task<>(null, Priority.Medium, LocalDate.of(2023, 10, 18), TaskStatus.Work),"");
    }



}
