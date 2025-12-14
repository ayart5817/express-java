package FreeTask.static_final;

// Класс НЕ static — он верхнеуровневый
public class GravityOnPlanet {

    // ✅ Константы: public static final — доступны без объекта, неизменяемы
    public static final double GRAVITY_JUPITER = 24.79; // м/с² (реальное значение)
    public static final double GRAVITY_EARTH  = 9.81;
    public static final double GRAVITY_MOON   = 1.62;

    // Перечисление планет — безопаснее строк
    public enum Planet {
        EARTH(GRAVITY_EARTH),
        MOON(GRAVITY_MOON),
        JUPITER(GRAVITY_JUPITER);

        private final double gravity;

        Planet(double gravity) {
            this.gravity = gravity;
        }

        public double getGravity() {
            return gravity;
        }
    }

    // ✅ Метод возвращает ВЕС (масса × гравитация)
    public static double calculateWeight(double mass, Planet planet) {
        if (mass < 0) {
            throw new IllegalArgumentException("Масса не может быть отрицательной");
        }
        return mass * planet.getGravity();
    }

    // Альтернатива: через строку (менее безопасно)
    public static double calculateWeight(double mass, String planetName) {
        double gravity;
        switch (planetName.toUpperCase()) {
            case "EARTH"  -> gravity = GRAVITY_EARTH;
            case "MOON"   -> gravity = GRAVITY_MOON;
            case "JUPITER"-> gravity = GRAVITY_JUPITER;
            default -> throw new IllegalArgumentException("Неизвестная планета: " + planetName);
        }
        return mass * gravity;
    }

    // Точка входа 
    public static void main(String[] args) {
        double mass = 70.0; // масса человека в кг

        // Вариант 1: через enum (рекомендуется)
        System.out.printf("Вес на Земле: %.2f Н%n", calculateWeight(mass, Planet.EARTH));
        System.out.printf("Вес на Луне: %.2f Н%n", calculateWeight(mass, Planet.MOON));
        System.out.printf("Вес на Юпитере: %.2f Н%n", calculateWeight(mass, Planet.JUPITER));

        // Вариант 2: через строку
        System.out.printf("Вес на Земле (строка): %.2f Н%n", calculateWeight(mass, "EARTH"));
    }
}