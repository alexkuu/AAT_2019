package service;

import Enums.Frameworks;

public class Config {
    private static final String PROPERTY_FILE = "application.properties";

    // Properties
    private static String url;
    private static String apiUrl;
    private static String browser;
    private static Frameworks framework;

    // Remote
    private static Boolean remoteRun;
    private static String remoteApp;

    // Selenium grid
    private static String gridHost;
    private static String gridPort;

    // Selenoid
    private static String selenoidHost;
    private static String selenoidPort;

    // API
    private static String apiClient;
    private static Boolean useProxy;

    // Integration
    private static String slackWebHook;
    private static String testRailUrl;
    private static String testRailUsername;
    private static String testRailPassword;
    private static String saucelabsUrl;

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

    public static boolean isRemoteRun() {
        if (remoteRun == null) {
            remoteRun = loadProperty("remote").toLowerCase().equals("true");
        }
        return remoteRun;
    }

    public static String getRemoteApp() {
        if (remoteApp == null) {
            remoteApp = loadProperty("remote.app");
        }
        return remoteApp;
    }

    public static String getGridHost() {
        if (gridHost == null) {
            gridHost = loadProperty("grid.host");
        }
        return gridHost;
    }

    public static String getGridPort() {
        if (gridPort == null) {
            gridPort = loadProperty("grid.port");
        }
        return gridPort;
    }

    public static String getSelenoidHost() {
        if (selenoidHost == null) {
            selenoidHost = loadProperty("selenoid.host");
        }
        return selenoidHost;
    }

    public static String getGSelenoidPort() {
        if (selenoidPort == null) {
            selenoidPort = loadProperty("selenoid.port");
        }
        return selenoidPort;
    }

    public static String getApiClient() {
        if (apiClient == null) {
            apiClient = loadProperty("api.client");
        }
        return apiClient;
    }

    public static boolean getUseProxy() {
        if (useProxy == null) {
            useProxy = loadProperty("api.use.proxy").toLowerCase().equals("true");
        }
        return useProxy;
    }

    public static String getSlackWebHook() {
        if (slackWebHook == null) {
            slackWebHook = loadProperty("slack.webhook");
        }
        return slackWebHook;
    }

    public static String getTestRailUrl() {
        if (testRailUrl == null) {
            testRailUrl = loadProperty("testrail.url");
        }
        return testRailUrl;
    }

    public static String getTestRailUsername() {
        if (testRailUsername == null) {
            testRailUsername = loadProperty("testrail.username");
        }
        return testRailUsername;
    }

    public static String getTestRailPassword() {
        if (testRailPassword == null) {
            testRailPassword = loadProperty("testrail.password");
        }
        return testRailPassword;
    }

    public static String getSaucelabsUrl() {
        if (saucelabsUrl == null) {
            saucelabsUrl = loadProperty("saucelabs.url");
        }
        return saucelabsUrl;
    }
}
