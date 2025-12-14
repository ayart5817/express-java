package FreeTask.JenericAndException;

public class Main {
    // Делим a на b. Если b == 0 — ошибка. Иначе — результат.
    public static Result<Double> divide(double a, double b) {
        try {
            if (b == 0) {
                throw new IllegalArgumentException("Деление на ноль");
            }
            return Result.success(a / b);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    public static void main(String[] args) {
        // Успешный случай
        Result<Double> r1 = divide(10, 2);
        System.out.println(r1); // Result.success(5.0)

        r1.ifSuccess(value -> System.out.println("Результат: " + value)); // → 5.0
        r1.ifFailure(err -> System.out.println("Ошибка: " + err.getMessage())); // не сработает

        // Ошибка
        Result<Double> r2 = divide(5, 0);
        System.out.println(r2); // Result.failure(IllegalArgumentException: Деление на ноль)

        r2.ifSuccess(value -> System.out.println("Результат: " + value)); // не сработает
        r2.ifFailure(err -> System.out.println("Ошибка: " + err.getMessage())); // → Деление на ноль

        // Получить "в лоб" — осторожно!
        // System.out.println(r2.get()); // ← упадёт с IllegalStateException!
    }
}