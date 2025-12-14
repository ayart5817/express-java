package ComplexTask2.Task3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Метод для добавления оценки (addGrade), который также валидирует оценку на предмет того, что она не отрицательна.
 *  * Метод для расчёта среднего значения оценок по конкретному предмету.
 *  * Обработка исключений через InvalidGradeException, если оценка некорректна.
 */

public class GradeService <T extends Number>{
    private final List<StudentGrade<T>> grades = new ArrayList<>();


    public synchronized void addGrade(StudentGrade<T> grade) throws InvalidGradeException {
        double value = grade.getGrade().doubleValue();
        if (grade == null) {
            throw new InvalidGradeException("Оценка = Null");
        }
        if (value < 0) {
            throw new InvalidGradeException("Оценка не может быть отрицательной " + value);
        }

        grades.add(grade);
    };

    // Мария География (4,)
    // Мария География (3.5)
    // Мария Физика (5) — получить среднее

    public  synchronized double avgGrade(String subject) throws InvalidGradeException {
        List<Double> subjectGrade = new ArrayList<>();
        for (StudentGrade<T> grade : grades) {
            if (subject.equals(grade.getSubject())) {
                subjectGrade.add(grade.getGrade().doubleValue());
            }
        }
        if (subjectGrade.isEmpty()) {
            throw new InvalidGradeException("Результат пуст, нет оценок");

        }

        double sum = 0;
        for (double grade : subjectGrade) {
            sum += grade;
        }
        return sum/subjectGrade.size();

    };
}
