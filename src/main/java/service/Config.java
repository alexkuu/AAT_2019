package service;

import Enums.Frameworks;

public class Config {
    private static final String PROPERTY_FILE = "application.properties";

    // Properties
    private static String url;
    private static String apiUrl;
    private static String browser;
    private static Frameworks framework;

    // Grid
    private static Boolean remoteRun;
    private static String remoteHost;
    private static String remotePort;

    // Widgets
    private static String topWidgetId;
    private static String bottomWidgetId;
    private static String overallStatisticsWidgetId;

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

    public static String getTopWidgetId() {
        if (topWidgetId == null) {
            topWidgetId = loadProperty("widgets.top.id");
        }
        return topWidgetId;
    }

    public static String getBottomWidgetId() {
        if (bottomWidgetId == null) {
            bottomWidgetId = loadProperty("widgets.bottom.id");
        }
        return bottomWidgetId;
    }

    public static String getOverallStatisticsWidgetId() {
        if (overallStatisticsWidgetId == null) {
            overallStatisticsWidgetId = loadProperty("widgets.overallstatistics.id");
        }
        return overallStatisticsWidgetId;
    }

    public static boolean isRemoteRun() {
        if (remoteRun == null) {
            remoteRun = loadProperty("remote").toLowerCase().equals("true");
        }
        return remoteRun;
    }

    public static String getRemoteHost() {
        if (remoteHost == null) {
            remoteHost = loadProperty("remote.host");
        }
        return remoteHost;
    }

    public static String getRemotePort() {
        if (remotePort == null) {
            remotePort = loadProperty("remote.port");
        }
        return remotePort;
    }
}
