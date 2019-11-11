package selenium.pageObjects;

import Interfaces.pageObjects.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import service.ui.DriverManager;

public class LoginPageSelenium implements LoginPage {


    @FindBy(how = How.XPATH, using = "//input[@placeholder='Login']")
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Login')]")
    private WebElement loginBtn;

    @FindBy(how = How.XPATH, using = "//div[contains(.,'An error occurred while connecting to server : You do not have enough permissions. Bad credentials')]")
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
