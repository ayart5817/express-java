package api.configs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private final Properties properties = new Properties();

    private Config() {
        // Пытаемся загрузить из разных мест
        InputStream input = null;

        // 1. Пробуем загрузить из test/resources
        input = getClass().getClassLoader().getResourceAsStream("config.properties");

        // 2. Если не нашли, пробуем из main/resources
        if (input == null) {
            input = Config.class.getResourceAsStream("/config.properties");
        }

        // 3. Если все еще не нашли, выбрасываем исключение с понятным сообщением
        if (input == null) {
            String userDir = System.getProperty("user.dir");
            String classpath = System.getProperty("java.class.path");
            throw new RuntimeException(
                    "config.properties not found!\n" +
                            "Current directory: " + userDir + "\n" +
                            "Please ensure the file exists at: src/test/resources/config.properties\n" +
                            "Or set system properties: -Dserver=http://localhost:4111 -DapiVersion=/api/v1"
            );
        }

        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        // Проверяем системные свойства первыми (можно переопределить через -D)
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }

        return INSTANCE.properties.getProperty(key);
    }

    public static String getBaseUrl() {
        String server = getProperty("server");
        String apiVersion = getProperty("apiVersion");

        return server + apiVersion;
    }


}