package ComplexTask2.Task3;


/** Создайте систему для управления и анализа оценок студентов, используя обобщённый подход.
 *  Система должна поддерживать различные типы числовых оценок, обеспечивать валидацию входных
 *  данных и предоставлять функционал для расчёта статистических показателей.
 *
 * Класс StudentGrade<T>:
 * Поля для имени студента, предмета и оценки.
 * Оценка должна быть типа T, который расширяет класс Number.
 * Конструктор для инициализации всех полей.
 * Геттеры для доступа к полям.
 * Сервис GradeService<T>:
 * Список List<StudentGrade<T>> для хранения оценок.
 * Метод для добавления оценки (addGrade), который также валидирует оценку на предмет того, что она не отрицательна.
 * Метод для расчёта среднего значения оценок по конкретному предмету.
 * Обработка исключений через InvalidGradeException, если оценка некорректна.
 */
public class StudentGrade<T extends Number> {
     private String studentName;
     private String subject;
     private T grade;

    public StudentGrade(String studentName, String subject, T grade) {
        this.studentName = studentName;
        this.subject = subject;
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubject() {
        return subject;
    }

    public T getGrade() {
        return grade;
    }
}
