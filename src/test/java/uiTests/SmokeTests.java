package uiTests;

import Pages.Home;
import Pages.Login;
import Pages.Settings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static service.UserFactory.getUser;

@Execution(ExecutionMode.CONCURRENT)
public class SmokeTests extends BaseTest {

    @DisplayName("Success login")
    @ParameterizedTest(name = "Login as {0}")
    @ValueSource(strings = {"user", "admin"})
    public void loginSuccess(String userRole) throws Exception {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(getUser(userRole));
        assertTrue(home.isDashBoardsListDisplayed());
        home.logout();
    }

    @DisplayName("Failed login")
    @Test
    public void loginFailed() {
        Login login = new Login();
        login.openHomePage();
        login.login(getUser("user").getName(), "wronG_PassW0RD");
        assertTrue(login.isErrorMessageDisplayed());
    }

    @DisplayName("Check settings access")
    @Test
    public void checkSettings() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(getUser("user"));
        home.openSettings();
        assertTrue(new Settings().isSettingsPageDisplayed());
        home.clickLogo();
        home.logout();
    }

    @DisplayName("Check dashboard workflow")
    @Test
    public void DashBoardAddEditDelete() {
        String dashBoardName = "dash_" + Math.random();
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(getUser("user"));
        home.addNewDashBoard(dashBoardName);
        assertTrue(home.isDashBoardDisplayed(dashBoardName));
        home.deleteDashBoard(dashBoardName);
        assertTrue(home.isDashBoardNotDisplayed(dashBoardName));
        home.logout();
    }
}
