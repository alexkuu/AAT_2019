package PageObjects;

import org.openqa.selenium.support.PageFactory;
import service.ui.DriverManager;

public class Pages {

    public static HomePage getHomePage(){
        return PageFactory.initElements(DriverManager.getDriver(), HomePage.class);
    }

    public static LoginPage getLoginPage(){
        return PageFactory.initElements(DriverManager.getDriver(), LoginPage.class);
    }

    public static SettingsPage getSettingsPage(){
        return PageFactory.initElements(DriverManager.getDriver(), SettingsPage.class);
    }
}
