package selenide.pageObjects;

import Interfaces.pageObjects.SettingsPage;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPageSelenide implements SettingsPage {
    @Override
    public boolean isSettingPageDisplayed() {
        return $(byXpath("//div[@id='headerBar']//span[text()='Settings']")).isDisplayed();
    }
}
