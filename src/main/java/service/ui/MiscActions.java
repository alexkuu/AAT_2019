package service.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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

    public static WebElement waitUntilElementIsExists(By by) {
        Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(by));
    }

    public static void waitUntilClickable(By by) {
        new WebDriverWait(DriverManager.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void dragNDropByOffset(By by, int x, int y) {
        dragNDropByOffset(DriverManager.getDriver().findElement(by), x, y);
    }

    public static void hoverElement1DragElement2(WebElement e1, WebElement e2, int x, int y) {
        new Actions(DriverManager.getDriver())
                .moveToElement(e1)
                .moveToElement(e2)
                .clickAndHold()
                .moveByOffset(x, y)
                .release()
                .build()
                .perform();
    }

    public static void hoverElement1DragElement2(By by1, By by2, int x, int y) {
        hoverElement1DragElement2(DriverManager.getDriver().findElement(by1), DriverManager.getDriver().findElement(by2), x, y);
    }

    public static void dragNDropByOffset(WebElement element, int x, int y) {
        new Actions(DriverManager.getDriver())
                .moveToElement(element)
                .clickAndHold()
                .moveByOffset(x, y)
                .release()
                .build()
                .perform();
    }

    public static void scrollTo(By by) {
        scrollTo(DriverManager.getDriver().findElement(by));
    }

    public static void scrollTo(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static boolean elementIsOnView(By by) {
        return elementIsOnView(DriverManager.getDriver().findElement(by));
    }

    public static boolean elementIsOnView(WebElement element) {
        Point elementCoordinates = element.getLocation();
        int x = elementCoordinates.getX();
        int y = elementCoordinates.getY();

        if (x < 0 || y < 0) {
            return false;
        }
        return x < DriverManager.getDriver().manage().window().getSize().getWidth() && y < DriverManager.getDriver().manage().window().getSize().getHeight();
    }

    public static void waitPageToLoad() {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> {
            return executor.executeScript("return document.readyState")
                    .toString().equals("complete");
        };
    }

    public static void changeImplicitWait(int seconds) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void scrollToTheBottom() {
        DriverManager.getDriver().findElement(By.tagName("body")).sendKeys(Keys.END);
    }

    public static String getCssValue(By by, String attr) {
        return DriverManager.getDriver().findElement(by).getCssValue(attr);
    }

    public static WebElement getByXpath(String xpath) {
        return DriverManager.getDriver().findElement(By.xpath(xpath));
    }

    public static void addAuthToken(String authToken) {
        String newToken = "bearer " + authToken;
        boolean tokenUpdated = false;
        while (!tokenUpdated) {
            JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
            executor.executeScript(String.format("window.localStorage.setItem('%s','%s');", "session_token", newToken));
            String currToken = executor.executeScript(String.format("return window.localStorage.getItem('%s');", "session_token")).toString();
            System.out.println("New token: " + newToken + "\nCur token: " + currToken + "\n");
            tokenUpdated = currToken.equals(newToken);
        }
    }
}
