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

    public static String makeScreenshot(String testName) {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String fileName = "test-output/" + testName + "_" + (System.currentTimeMillis() / 1000L) + "_screenshot.png";
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            fileName = "<error>";
            e.printStackTrace();
        }
        return fileName;
    }

}
