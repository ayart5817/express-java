package FreeTask.JenericAndException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Пользовательское исключение
class NegativeValueException extends RuntimeException {
    public NegativeValueException(String message) {
        super(message);
    }
}

public class PositiveNumber<T extends Number> {
    // Инкапсулируем список — делаем private и final
    private final List<T> list = new ArrayList<>();

    // ✅ Строго положительные: > 0 (не ≥ 0)
    private boolean isPositive(T number) {
        if (number == null) {
            return false;
        }
        return number.doubleValue() > 0;  // >, а не >=
    }

    // Публичный безопасный метод добавления
    public void add(T number) {
        if (!isPositive(number)) {
            throw new NegativeValueException("Невалидное число: " + number + " (требуется > 0)");
        }
        list.add(number);
    }

    // Публичный только read-only доступ
    public List<T> getList() {
        return Collections.unmodifiableList(list);
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    // ✅ Исправлено: public static void main
    public static void main(String[] args) {
        // Указываем тип при создании — никаких raw types!
        PositiveNumber<Double> pNumber = new PositiveNumber<>();

        // ✅ Корректное добавление через ваш метод
        pNumber.add(20.0);        // OK
        System.out.println(pNumber); // [20.0]

        // pNumber.add(-5.0);     // → NegativeValueException
        // pNumber.add(0.0);      // → тоже ошибка (ноль не положительный)

        // ❌ Следующие строки НЕ СКОМПИЛИРУЮТСЯ — и это хорошо!
        // pNumber.add("20");          // ошибка компиляции: String не Number
        // pNumber.getList().add(5.0); // ошибка: unmodifiable list

        // Пример с Integer
        PositiveNumber<Integer> intPos = new PositiveNumber<>();
        intPos.add(100);
        // intPos.add(-1); // Exception

        System.out.println("Double list: " + pNumber);
        System.out.println("Integer list: " + intPos);
    }
}