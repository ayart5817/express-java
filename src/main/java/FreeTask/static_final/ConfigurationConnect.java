package FreeTask.static_final;

public class ConfigurationConnect {
    private final String HOST;
    private final String PORT;
    private final String DB_NAME;


    public ConfigurationConnect(String HOST, String PORT, String DB_NAME) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.DB_NAME = DB_NAME;
    }

    public String getConfigurationConnect() {
        return HOST+PORT+DB_NAME;
    }

    static void main() {
        ConfigurationConnect configurationConnect = new ConfigurationConnect("123.123.123.256", ":6001",":CORE");
        System.out.println(configurationConnect.getConfigurationConnect());
    }
}
