package FreeTask.Collection;
import java.util.List;


import java.util.*;

public class CategoryStructure33 {
    // Основная структура:
    // Категория (String) →
    //   Подкатегория (String) →
    //     Список элементов (List<String>)
    static Map<String, Map<String, List<String>>> data = new HashMap<>();

    // Добавить элемент в категорию и подкатегорию
    static void add(String category, String subcategory, String item) {
        // 1. Получаем или создаём мапу подкатегорий для категории
        Map<String, List<String>> subcategories =
                data.computeIfAbsent(category, k -> new HashMap<>());

        // 2. Получаем или создаём список элементов для подкатегории
        List<String> items = subcategories.computeIfAbsent(subcategory, k -> new ArrayList<>());

        // 3. Добавляем элемент
        items.add(item);
        System.out.println("Добавлено: [" + category + "] → [" + subcategory + "] → " + item);
    }

    // Получить все элементы по категории и подкатегории
    static List<String> get(String category, String subcategory) {
        return data.getOrDefault(category, Collections.emptyMap())
                .getOrDefault(subcategory, Collections.emptyList());
    }

    static void main(String[] args) {
// Заполняем данные
        add("Электроника", "Телефоны", "iPhone 15");
        add("Электроника", "Телефоны", "Samsung Galaxy S24");
        add("Электроника", "Ноутбуки", "MacBook Air");
        add("Книги", "Фантастика", "Гарри Поттер");
        add("Книги", "Фантастика", "Властелин Колец");
        add("Книги", "Детективы", "Шерлок Холмс");

        System.out.println("Телефоны: " + get("Электроника", "Телефоны"));


    }
}