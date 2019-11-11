package selenium.pageObjects;

import Interfaces.pageObjects.SettingsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SettingsPageSelenium implements SettingsPage {

    @FindBy(how = How.XPATH, using = "//div[@id='headerBar']//span[text()='Settings']")
    private WebElement pageTitle;

    public SettingsPageSelenium() {
    }

    @Override
    public boolean isSettingPageDisplayed() {
        return pageTitle.isDisplayed();
    }
}
