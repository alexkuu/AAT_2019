package service.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MiscActions {

    public static void moveMouseToElement(By by) {
        moveMouseToElement(DriverManager.getDriver().findElement(by));
    }

    public static void moveMouseToElement(WebElement element) {
        new Actions(DriverManager.getDriver()).moveToElement(element)
                .build()
                .perform();
    }

    public static void jsClick(By by) {
        jsClick(DriverManager.getDriver().findElement(by));
    }

    public static void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public static void waitUntilElementIsExists(By by) {
        Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        WebElement foo = wait.until(driver -> driver.findElement(by));
    }

    public static void waitUntilClickable(By by) {
        new WebDriverWait(DriverManager.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(by));
    }
}
