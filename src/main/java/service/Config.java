package service;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.LeafPropertyLoader;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String PROPERTY_FILE = "/application.properties";

    // Properties
    private static String url;
    private static String apiUrl;
    private static String browser;
    private static String userName;
    private static String userPassword;
    private static String adminName;
    private static String adminPassword;

    private Config() {}

    public static String loadProperty(String name) {
        if (name == null || name.trim().length() == 0) {
            return null;
        }

        Properties props = new Properties();
        try {
            props.load(LeafPropertyLoader.class.getResourceAsStream(PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props.getProperty(name);
    }

    public static String getUrl() {
        if(url == null){
            url = loadProperty("site.url");
        }
        return url;
    }

    public static String getBrowser() {
        if(browser == null){
            browser = loadProperty("browser.name");
        }
        return browser;
    }

    public static String getApiUrl() {
        if(apiUrl == null){
            apiUrl = loadProperty("api.url");
        }
        return apiUrl;
    }

    public static String getUserName() {
        if(userName == null){
            userName = loadProperty("user.name");
        }
        return userName;
    }

    public static String getUserPassword() {
        if(userPassword == null){
            userPassword = loadProperty("user.password");
        }
        return userPassword;
    }

    public static String getAdminName() {
        if(adminName == null){
            adminName = loadProperty("admin.name");
        }
        return adminName;
    }

    public static String getAdminPassword() {
        if(adminPassword == null){
            adminPassword = loadProperty("admin.password");
        }
        return adminPassword;
    }
}
