package ComplexTask.task1;

// Интерфейс, который определяет метод для сокращения URL. Реализации могут включать Base62, хеширование, UUID и другие
public interface ShorteningStrategy {
String shorten(String originalUrl);
}
