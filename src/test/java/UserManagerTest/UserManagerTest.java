package UserManagerTest;

import ComplexTask2.Task1.Students;
import ComplexTask2.Task1.UserManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {
Students studentsTest = new Students(20, "Марк", false);

    @Test
    public void testMainChekUserManager() {
        UserManager<Students> userManager = new UserManager<>();
        userManager.add(studentsTest);
        List<Students> actualStudent = userManager.getAll();

        //имя совпадает
        assertEquals("Марк", studentsTest.getName());
        //Список содержит 1 элемент
        assertEquals(1,actualStudent.size());
        //Поиск по имени
        userManager.add(new Students(19,"Марта", true));
        userManager.add(new Students(30, "Олег", true));
        List<Students> foundByName = userManager.filterByName("Марк");
        assertEquals("Марк", foundByName.getFirst().getName());
        //Получение НЕ активных пользователей
        List<Students> getIsActive = userManager.getActiveUsers(true);
        assertTrue(getIsActive.stream().allMatch(user -> user.isActive()));
        //Фильтр по возрасту
        List<Students> byAge = userManager.filterByAge(19,25);
        assertEquals(2, byAge.size());
        //удаляем объект "Марк"
        assertTrue(userManager.remove(studentsTest));
        assertEquals(2, userManager.getAll().size());
    }

    @Test
    public void InvalidInputValueUserManager() {
        UserManager<Students> userManager = new UserManager<>();
        userManager.add(studentsTest);
        List<Students> actualStudent = userManager.getAll();
        assertEquals(1, actualStudent.size());

        //имя НЕ совпадает
        assertNotEquals("Март", studentsTest.getName());

        //Поиск по имени без совпадения
        userManager.add(new Students(19,"Марта", true));
        userManager.add(new Students(30, "Олег", true));
        List<Students> foundByName = userManager.filterByName("Олек");
        assertTrue(foundByName.isEmpty(), "Должен быть пустой список, так как 'Олек' не существует");

        //получение НЕ активных пользователей
        List<Students> getIsActive = userManager.getActiveUsers(false);
        assertFalse(getIsActive.stream().allMatch(user -> user.isActive()));

        //Фильтр по возрасту результат 0
        List<Students> byAge = userManager.filterByAge(18,25);
        assertEquals(2, byAge.size());

        // Удаляем объект "Марк"
        assertTrue(userManager.remove(studentsTest));

        // Проверяем, что его больше нет
        List<Students> found = userManager.filterByName("Марк");
        assertTrue(found.isEmpty(), "Марк должен быть удалён");

        //Список содержит 0 элементов
        List<Students> all = userManager.getAll();
        for (Students student : all) {
            userManager.remove(student);
        }
        assertEquals(0, userManager.getAll().size());

        //попытка добавить null в объект
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            userManager.add(null);
        });



    }





}


