package ComplexTask.task1;

//Класс, использующий Factory Method для создания объектов ShorteningStrategy
public class ShortenerFactory {
    public static ShorteningStrategy createStrategy(String type) {
        return switch (type.toLowerCase()) {
            case "base62" -> new Base62Strategy();
            case "hash" -> new HashStrategy();
            case "uuid" -> new UUIDStrategy();
            default -> throw new IllegalArgumentException("Неизвестная стратегия " + type);
        };
    }
}
