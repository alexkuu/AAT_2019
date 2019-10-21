package PageObjects;

import org.openqa.selenium.support.PageFactory;
import service.ui.Driver;

public class Pages {

    public static HomePage getHomePage(){
        return PageFactory.initElements(Driver.getDriver(), HomePage.class);
    }

    public static LoginPage getLoginPage(){
        return PageFactory.initElements(Driver.getDriver(), LoginPage.class);
    }

    public static SettingsPage getSettingsPage(){
        return PageFactory.initElements(Driver.getDriver(), SettingsPage.class);
    }
}
