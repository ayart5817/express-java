package ComplexTask.task1;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUrlStorage implements UrlStorage {
    private static InMemoryUrlStorage instance;
    private final Map<String, String> shortToOriginalMap;


    private InMemoryUrlStorage() {
        shortToOriginalMap = new HashMap<>();


    }

    public static InMemoryUrlStorage getInstance() {
        if (instance == null) {
            instance = new InMemoryUrlStorage();
        }
        return instance;
    }


    @Override
    public void save(String shortUrl, String originalUrl) {
        shortToOriginalMap.put(shortUrl, originalUrl);

    }

    @Override
    public String getOriginalUrl(String shortKey) {
        return shortToOriginalMap.get(shortKey);
    }

    @Override
    public boolean contains(String shortKey) {
        return shortToOriginalMap.containsKey(shortKey);
    }
}
