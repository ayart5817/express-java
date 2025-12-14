package FreeTask.static_final;

public class ConfigurationParameterGlobal {
    static public double timeout;
    static public String  apiUrl;

    public ConfigurationParameterGlobal() {
        throw new UnsupportedOperationException("Клас не предназначен для инстанцирования");
    }

    static void addTimeout(double n) {
        timeout = n;
    }
    static void addApiUrl(String x) {
        apiUrl = x;
    }

    public static double getTimeout() {
        return timeout;
    }

    public static void setTimeout(double timeout) {
        if (timeout < 0) {throw  new IllegalArgumentException("Значение не может быть меньше 0");}
        ConfigurationParameterGlobal.timeout = timeout;
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public static void setApiUrl(String apiUrl) {
        if (apiUrl == null || apiUrl.trim().isEmpty() ) {throw  new IllegalArgumentException("Значение не может быть пустым");}
        ConfigurationParameterGlobal.apiUrl = apiUrl;
    }
}
