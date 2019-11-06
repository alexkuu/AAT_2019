package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import service.ui.DriverManager;

public class BasePage {

    final static Logger logger = Logger.getLogger(BasePage.class);

    public String getTitle(){
        return DriverManager.getDriver().getTitle();
    }

    public void jsClick(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)DriverManager.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }


    public void openHomePage(){
        DriverManager.get().goTo("");
    }
}
