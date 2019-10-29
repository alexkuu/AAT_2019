package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {


    @FindBy(how = How.XPATH, using = "//input[@placeholder='Login']")
    public WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Password']")
    public WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Login')]")
    public WebElement loginBtn;

    @FindBy(how = How.XPATH, using = "//div[contains(.,'An error occurred while connecting to server : You do not have enough permissions. Bad credentials')]")
    public WebElement loginError;

    public LoginPage(){
    }
}
