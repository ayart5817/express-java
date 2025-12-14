package GradeServiceTest;

import ComplexTask2.Task3.GradeService;
import ComplexTask2.Task3.InvalidGradeException;
import ComplexTask2.Task3.StudentGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeServiceTest {


    /**
     * Система должна поддерживать различные типы числовых оценок, обеспечивать валидацию входных данных и предоставлять функционал для расчёта статистических показателей.
     * Геттеры для доступа к полям.
     * Метод для добавления оценки (addGrade), который также валидирует оценку на предмет того, что она не отрицательна.
     * Метод для расчёта среднего значения оценок по конкретному предмету
     * Обработка исключений через InvalidGradeException, если оценка некорректна
     */
    GradeService<Double> service;
    @BeforeEach
            void setUp() {
        GradeService<Double> service = new GradeService<>();
       try {
       service.addGrade(new StudentGrade<>("Иван", "География", 4.99));
        service.addGrade(new StudentGrade<>("Иван", "Математика", 3.0));
        service.addGrade(new StudentGrade<>("Иван", "Физика", 4.99));
        service.addGrade(new StudentGrade<>("Маша", "География", 3.2));
        service.addGrade(new StudentGrade<>("Маша", "Математика", 5.0));
        service.addGrade(new StudentGrade<>("Маша", "Физика", 2.0));
        } catch (InvalidGradeException e) {
           fail("Исключение при инициализации" + e.getMessage());
       }

    }


    @Test //add Int
    public void addGradeService() throws InvalidGradeException {
        GradeService<Integer> serviceTestInt = new GradeService<>();
        try {
            serviceTestInt.addGrade(new StudentGrade<Integer>("Петя", "Химия", 5));
            serviceTestInt.addGrade(new StudentGrade<Integer>("Петя", "Химия", 0));
        } catch (InvalidGradeException e) {
            fail("Вызвано Исключение");
        }
        //AVG CHECK
        assertEquals(2.5, serviceTestInt.avgGrade("Химия") );
    }

    @Test //Add -5
    public void addNegativeGrade()  {
        assertThrows(NullPointerException.class, () -> service.addGrade(new StudentGrade<>("Вася", "Химия", -1.0)));
    }

    @Test //проверка геттеров
    public void CheckGetterStudentGrate() {
        StudentGrade<Double> studentGrade = new StudentGrade<>("Гена", "Математика", 0.8);
        String name = "Гена";
        String subject = "Математика";
        Double grade = 0.8;

        assertEquals(name, studentGrade.getStudentName());
        assertEquals(subject, studentGrade.getSubject());
        assertEquals(grade, studentGrade.getGrade());
    }

    @Test // Exception null
    public void exceptionCheckNullService() {
       assertThrows(NullPointerException.class, () -> service.addGrade(new StudentGrade<>(null, "Биология", 5.0)));
        assertThrows(NullPointerException.class, () -> service.addGrade(new StudentGrade<>("Данил", null, 5.9)));
        assertThrows(NullPointerException.class, () -> service.addGrade(new StudentGrade<>("Данил", "Биология", null)));
    }
}
