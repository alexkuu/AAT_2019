package Pages;

import Enums.Users;
import Interfaces.pageObjects.LoginPage;
import PageObjects.Pages;
import service.User;
import service.api.ApiClient;
import service.ui.MiscActions;

import static service.UserFactory.getUser;

public class Login extends BasePage {

    private LoginPage obj;
    ApiClient api;

    public Login() {
        obj = new Pages().getLoginPage();
    }

    public void openHomePage() {
        obj.openHomePage();
        MiscActions.waitPageToLoad();
    }

    public void typeUser(String username) {
        obj.inputUserName(username);
    }

    public void typePassword(String password) {
        obj.inputPassword(password);
    }

    public void submitLogin() {
        obj.submitLogin();
    }

    public void login(User user) {
        login(user.getName(), user.getPassword());
    }

    public void login(Users user) {
        login(user.toString().toLowerCase());
    }

    public void login(String role) {
        login(getUser(role));
    }

    public void login(String username, String password) {
        typeUser(username);
        typePassword(password);
        submitLogin();
        logger.info("Login as:  " + username);
    }

    public ApiClient loginWithApi(Users role) {
        openHomePage();
        api = new ApiClient(role);
        String authToken = api.getToken();
        MiscActions.addAuthToken(authToken);
        openHomePage();
        return api;
    }

    public ApiClient loginWithApi(ApiClient apiClient) {
        openHomePage();
        String authToken = apiClient.getToken();
        MiscActions.addAuthToken(authToken);
        openHomePage();
        return apiClient;
    }

    public void logoutWithApi() {
        api.user.logout();
    }

    public void logoutWithApi(ApiClient apiClient) {
        apiClient.user.logout();
    }

    public boolean isErrorMessageDisplayed() {
        return obj.loginErrorDisplayed();
    }
}
