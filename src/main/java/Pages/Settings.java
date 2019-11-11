package Pages;

import Interfaces.pageObjects.SettingsPage;
import PageObjects.Pages;

public class Settings extends BasePage {


    private static SettingsPage obj;

    public Settings() {
        obj = new Pages().getSettingsPage();
    }

    public boolean isSettingsPageDisplayed() {
        return obj.isSettingPageDisplayed();
    }

}
