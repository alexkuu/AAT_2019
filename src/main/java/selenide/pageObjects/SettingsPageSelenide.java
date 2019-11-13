package selenide.pageObjects;

import Interfaces.pageObjects.SettingsPage;
import constants.Xpathes;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPageSelenide implements SettingsPage {
    @Override
    public boolean isSettingPageDisplayed() {
        return $(byXpath(Xpathes.SETTINGS_PAGE_TITLE)).isDisplayed();
    }
}
