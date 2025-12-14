package Pattern.task1;

public class ConfigurationManager {
    private static ConfigurationManager instance;


    private String dbUrl;
    private String dbUser;
    private String dbPass;
    private String filePath;
    private String logLevel;
    private String logFile;


    private ConfigurationManager() {
        System.out.println("Загрузка конфигурации");
        loadConfiguration();
    }

    //Метод для загрузки конфигурации

    private void loadConfiguration() {
        this.dbUrl = "db.url";
        this.dbUser = "admin";
        this.dbPass = "12345";
        this.filePath = "/var/env";
        this.logLevel = "INFO";
        this.logFile = "/logs";
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
    return instance;
    }

    @Override
    public String toString() {
        return "ConfigurationManager{" +
                "dbUrl='" + dbUrl + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPass='" + dbPass + '\'' +
                ", filePath='" + filePath + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", logFile='" + logFile + '\'' +
                '}';
    }
}