package service;

public class Config {
    private static final String PROPERTY_FILE = "application.properties";

    // Properties
    private static String url;
    private static String apiUrl;
    private static String browser;

    private static PropReader pReader = new PropReader(PROPERTY_FILE);

    private Config() {
    }

    private static String loadProperty(String name) {
        return pReader.readProperty(name);
    }

    public static String getUrl() {
        if (url == null) {
            url = loadProperty("site.url");
        }
        return url;
    }

    public static String getBrowser() {
        if (browser == null) {
            browser = loadProperty("browser.name");
        }
        return browser;
    }

    public static String getApiUrl() {
        if (apiUrl == null) {
            apiUrl = loadProperty("api.url");
        }
        return apiUrl;
    }
}
