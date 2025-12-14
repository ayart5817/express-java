package EntityManagerTest;


import ComplexTask2.EntityManager;
import ComplexTask2.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityManagerTest {



 ///  тест кейсы
 ///  Добавить  элемент:
 /// - добавить 1 элемент в пустой менеджер
 /// - добавить 1 элемент в НЕ пустой менеджер
 /// - многопоточность  (проверка интеграции)
 ///
 ///

@Test
    void addEntityTest() {

     EntityManager<Student> manager = new EntityManager<>();
    int initializingSize = manager.getAll().size();

     Student expected = new Student(17, true, "Kolya");
     manager.add(expected);
     Student actualStudent =  manager.getAll().getFirst();
     assertEquals(expected, actualStudent); //
    assertEquals(expected.getAge(), actualStudent.getAge());
    assertEquals(expected.getName(), actualStudent.getName());
    assertEquals(expected.isActive(), actualStudent.isActive());
    assertEquals(initializingSize + 1, manager.getAll().size());
 }




}
