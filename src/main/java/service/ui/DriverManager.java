package service.ui;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<Driver> threadLocalDriver = new ThreadLocal<>();

    public static Driver get() {
        return threadLocalDriver.get();
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get().getDriver();
    }

    public static void set(Driver driver) {
        threadLocalDriver.set(driver);
    }

}
