package Interfaces.pageObjects;

public interface LoginPage {

    void openHomePage();
    void inputUserName(String name);
    void inputPassword(String password);
    void submitLogin();
    boolean loginErrorDisplayed();

}
