package Pages;

import PageObjects.LoginPage;
import PageObjects.Pages;
import service.User;

import static service.UserFactory.getUser;

public class Login extends BasePage{

    LoginPage obj;

    public Login() {
        obj = new Pages().getLoginPage();
    }

    public void openHomePage(){
        obj.openHomePage();
    }

    public void typeUser(String username){
        obj.usernameField.clear();
        obj.usernameField.sendKeys(username);
    }

    public void typePassword(String password){
        obj.passwordField.clear();
        obj.passwordField.sendKeys(password);
    }

    public void submitLogin(){
        obj.loginBtn.click();
    }

    public void login(User user) {
        login(user.getName(), user.getPassword());
    }

    public void login(String role){
        login(getUser(role));
    }

    public void login(String username, String password) {
        typeUser(username);
        typePassword(password);
        submitLogin();
        logger.info("Login as:  " + username);
    }

    public boolean isErrorMessageDisplayed(){
        return obj.loginError.isDisplayed();
    }
}
