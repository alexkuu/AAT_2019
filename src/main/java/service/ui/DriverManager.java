package service.ui;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

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

    public static void makeScreenshot(String testName) {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("test-output/" + testName + "_" + (System.currentTimeMillis() / 1000L) + "_screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
