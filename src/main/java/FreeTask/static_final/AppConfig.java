package FreeTask.static_final;

public class AppConfig {
    // ЕДИНСТВЕННОЕ глобальное значение — static
    private static String development = "Dev";

    // Запрещаем создание экземпляров
    private AppConfig(String development) {
    // Приватный конструктор
    }

    public  static Object getDevelopment() {
        return development;
    };

    // Получить текущее окружение
    public static void setDevelopment(String env) {
        if ("Dev".equals(env) || "Dev1".equals(env) || "Stage".equals(env)) {
            development=env;
        } else {
            System.out.println("Ошибка назначения: неверное назначение");
        };

    }

    static void main() {
        System.out.println("Изначально: " + AppConfig.getDevelopment()); // Dev

        AppConfig.setDevelopment("Dev1");
        System.out.println("После изменения: " + getDevelopment()); // Dev1

        AppConfig.setDevelopment("Stage");
        System.out.println("Ещё раз: " + AppConfig.getDevelopment()); // Stage
    }

}
