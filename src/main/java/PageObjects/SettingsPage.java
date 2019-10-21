package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SettingsPage {

    @FindBy(how = How.XPATH, using = "//div[@id='headerBar']//span[text()='Settings']")
    public WebElement pageTitle;

    public SettingsPage(){
    }
}
