package ComplexTask.task1;

// Класс, отвечающий за логику сокращения и восстановления URL.
public class UrlShortenerService {
    private final UrlStorage storage;
    private ShorteningStrategy strategy;



    public UrlShortenerService(ShorteningStrategy strategy) {
        this.strategy = strategy;
        this.storage = InMemoryUrlStorage.getInstance();
    }

    //основной конструктор
    public UrlShortenerService(UrlStorage storage, ShorteningStrategy strategy, String domain) {
        this.storage = storage;
        this.strategy = strategy;

    }

    //вызов основного метода сокращения
    public String shortenUrl(String originalUrl) {
        String shortCode = strategy.shorten(originalUrl);
        if (storage.contains(shortCode)) {
            return  storage.getOriginalUrl(shortCode);

        }
        storage.save(shortCode, originalUrl);

        return shortCode;


    }

    //восстановление URL
    public String expandUrl(String shortCode) {

        String originalUrl = storage.getOriginalUrl(shortCode);
        if (originalUrl == null) {
            throw new IllegalArgumentException("URL не найден — " + shortCode);
        }
        return originalUrl;
    }
}
