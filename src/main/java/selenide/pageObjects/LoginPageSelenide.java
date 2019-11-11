package selenide.pageObjects;

import Interfaces.pageObjects.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import service.Config;
import service.ui.DriverManager;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPageSelenide implements LoginPage {


    public LoginPageSelenide(){
        WebDriverRunner.setWebDriver(DriverManager.getDriver());
    }

    @Override
    public void openHomePage() {
        open(Config.getUrl());
    }

    @Override
    public void inputUserName(String name) {
        $(byAttribute("placeholder", "Login")).setValue(name);
    }

    @Override
    public void inputPassword(String password) {
        $(byAttribute("placeholder", "Password")).setValue(password);
    }

    @Override
    public void submitLogin() {
        $(byText("Login")).click();
    }

    @Override
    public boolean loginErrorDisplayed() {
        return $(byText("An error occurred while connecting to server : You do not have enough permissions. Bad credentials")).isDisplayed();
    }
}
