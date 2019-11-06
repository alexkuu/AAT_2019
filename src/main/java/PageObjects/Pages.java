package PageObjects;

import org.openqa.selenium.support.PageFactory;
import service.ui.DriverManager;

public class Pages {

    public Pages(){}

    public HomePage getHomePage(){
        return PageFactory.initElements(DriverManager.getDriver(), HomePage.class);
    }

    public LoginPage getLoginPage(){
        return PageFactory.initElements(DriverManager.getDriver(), LoginPage.class);
    }

    public SettingsPage getSettingsPage(){
        return PageFactory.initElements(DriverManager.getDriver(), SettingsPage.class);
    }
}
