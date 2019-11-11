package PageObjects;

import Enums.Frameworks;
import Interfaces.pageObjects.HomePage;
import Interfaces.pageObjects.LoginPage;
import Interfaces.pageObjects.SettingsPage;
import org.openqa.selenium.support.PageFactory;
import selenide.pageObjects.HomePageSelenide;
import selenide.pageObjects.LoginPageSelenide;
import selenide.pageObjects.SettingsPageSelenide;
import selenium.pageObjects.HomePageSelenium;
import selenium.pageObjects.LoginPageSelenium;
import selenium.pageObjects.SettingsPageSelenium;
import service.Config;
import service.ui.DriverManager;

public class Pages {

    public Pages() {}

    public HomePage getHomePage() {
        if (Config.getFramework() == Frameworks.SELENIUM) {
            return PageFactory.initElements(DriverManager.getDriver(), HomePageSelenium.class);
        }
        if(Config.getFramework() == Frameworks.SELENIDE){
            return new HomePageSelenide();
        }
        return null;
    }

    public LoginPage getLoginPage() {
        if (Config.getFramework() == Frameworks.SELENIUM) {
            return PageFactory.initElements(DriverManager.getDriver(), LoginPageSelenium.class);
        }
        if(Config.getFramework() == Frameworks.SELENIDE){
            return new LoginPageSelenide();
        }
        return null;
    }

    public SettingsPage getSettingsPage() {
        if (Config.getFramework() == Frameworks.SELENIUM) {
            return PageFactory.initElements(DriverManager.getDriver(), SettingsPageSelenium.class);
        }
        if(Config.getFramework() == Frameworks.SELENIDE){
            return new SettingsPageSelenide();
        }
        return null;
    }
}
