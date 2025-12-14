package FreeTask.Collection;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CategoryManager27 {
    private static Map<String, Set<String>> catalog = new TreeMap<>();

    static void addSubCatalog(String category, String subcat) {
        catalog.computeIfAbsent(category, k -> new TreeSet<>()).add(subcat);
    }

   static public void printAll() {
        for (String category : catalog.keySet()) {
            System.out.println("📁 " + category + ":");
            for (String sub : catalog.get(category)) {
                System.out.println("  └─ " + sub);
            }
        }
    }

    public static void main(String[] args) {
        CategoryManager27 cm = new CategoryManager27();

        CategoryManager27.addSubCatalog("Электроника", "Телефоны");
        CategoryManager27.addSubCatalog("Электроника", "Ноутбуки");
        CategoryManager27.addSubCatalog("Электроника", "Наушники");

        CategoryManager27.addSubCatalog("Книги", "Фантастика");
        CategoryManager27.addSubCatalog("Книги", "Детективы");
        CategoryManager27.addSubCatalog("Книги", "Учебники");

        CategoryManager27.addSubCatalog("Одежда", "Обувь");
        CategoryManager27.addSubCatalog("Одежда", "Верхняя одежда");
        CategoryManager27.addSubCatalog("Одежда", "Аксессуары");

        CategoryManager27.printAll();
    }
}