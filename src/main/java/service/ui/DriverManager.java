package service.ui;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<Driver> threadLocalDriver = new ThreadLocal<>();

    public final static Driver get() {
        return threadLocalDriver.get();
    }

    public final static WebDriver getDriver() {
        return threadLocalDriver.get().getDriver();
    }

    public final static void set(Driver driver) {
        threadLocalDriver.set(driver);
    }

}
