package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import service.ui.Driver;

public class BasePage {

    final static Logger logger = Logger.getLogger(BasePage.class);

    public static String getTitle(){
        return Driver.getTitle();
    }

    public static void jsClick(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)Driver.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }
}
