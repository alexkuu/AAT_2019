package Pages;

import PageObjects.Pages;
import PageObjects.SettingsPage;

public class Settings extends BasePage{


    private SettingsPage obj;

    public Settings(){
        obj = new Pages().getSettingsPage();
    }

    public boolean isSettingsPageDisplayed(){
        return obj.pageTitle.isDisplayed();
    }

}
