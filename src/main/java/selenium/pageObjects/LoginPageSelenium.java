package selenium.pageObjects;

import Interfaces.pageObjects.LoginPage;
import constants.Xpathes;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import service.ui.DriverManager;

public class LoginPageSelenium implements LoginPage {


    @FindBy(how = How.XPATH, using = Xpathes.LOGIN_INPUT)
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = Xpathes.PASSWORD_INPUT)
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = Xpathes.LOGIN_BUTTON)
    private WebElement loginBtn;

    @FindBy(how = How.XPATH, using = Xpathes.LOGIN_ERROR_MESSAGE)
    private WebElement loginError;

    public LoginPageSelenium(){
    }

    public void openHomePage(){
        DriverManager.get().goTo("");
    }

    @Override
    public void inputUserName(String name) {
        usernameField.clear();
        usernameField.sendKeys(name);
    }

    @Override
    public void inputPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Override
    public void submitLogin() {
        loginBtn.click();
    }

    @Override
    public boolean loginErrorDisplayed() {
        return loginError.isDisplayed();
    }
}
