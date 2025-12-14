package ComplexTask.task1;
//Интерфейс для хранения пар "длинный URL - короткий URL", с различными реализациями (например, память, файлы, базы данных).
public interface UrlStorage {
    void save(String shortKey, String longUrl);
    String getOriginalUrl(String shortKey);
    boolean contains(String shortKey);
}
