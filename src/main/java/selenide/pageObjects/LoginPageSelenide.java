package selenide.pageObjects;

import Interfaces.pageObjects.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import constants.AppTexts;
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
        $(byAttribute("placeholder", AppTexts.LOGIN)).setValue(name);
    }

    @Override
    public void inputPassword(String password) {
        $(byAttribute("placeholder", AppTexts.PASSWORD)).setValue(password);
    }

    @Override
    public void submitLogin() {
        $(byText(AppTexts.LOGIN)).click();
    }

    @Override
    public boolean loginErrorDisplayed() {
        return $(byText(AppTexts.LOGIN_ERROR_MESSAGE)).isDisplayed();
    }
}
