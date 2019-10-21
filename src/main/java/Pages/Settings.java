package Pages;

import PageObjects.HomePage;
import PageObjects.Pages;
import PageObjects.SettingsPage;

public class Settings extends BasePage{


    static SettingsPage obj;

    public Settings(){
        obj = Pages.getSettingsPage();
    }

    public boolean isSettingsPageDisplayed(){
        return obj.pageTitle.isDisplayed();
    }

}
