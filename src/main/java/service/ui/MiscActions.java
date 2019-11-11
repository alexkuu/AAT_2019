package service.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MiscActions {

    public static void moveMouseToElement(WebElement element) {
        new Actions(DriverManager.getDriver()).moveToElement(element)
                .build()
                .perform();
    }

    public static void jsClick(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)DriverManager.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }
}
