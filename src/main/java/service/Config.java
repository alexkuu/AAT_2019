package service;

import Enums.Frameworks;

public class Config {
    private static final String PROPERTY_FILE = "application.properties";

    // Properties
    private static String url;
    private static String apiUrl;
    private static String browser;
    private static Frameworks framework;

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

    public static Frameworks getFramework() {
        if (framework == null) {
            String framework_as_string = loadProperty("framework");
            if (framework_as_string.equals(Frameworks.SELENIUM.toString().toLowerCase())) {
                framework = Frameworks.SELENIUM;
            } else if (framework_as_string.equals(Frameworks.SELENIDE.toString().toLowerCase())) {
                framework = Frameworks.SELENIDE;
            }
        }
        return framework;
    }
}
