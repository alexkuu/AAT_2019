package uiTests;

import Pages.Home;
import Pages.Login;
import Pages.Settings;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static service.UserFactory.getUser;

@RunWith(JUnitParamsRunner.class)
public class SmokeTests extends BaseTest {

    @Parameterized.Parameters
    public static Object[] users() {
        return new Object[] { "admin", "user" };
    }

    @Test
    @Parameters(method = "users")
    public void loginSuccess(String userRole) throws Exception {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(getUser(userRole));
        assertTrue(home.isDashBoardsListDisplayed());
        home.logout();
    }

    @Test
    public void loginFailed() {
        Login login = new Login();
        login.openHomePage();
        login.login(getUser("user").getName(), "wronG_PassW0RD");
        assertTrue(login.isErrorMessageDisplayed());
    }

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

    @Test
    public void DemoDashboard(){
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(getUser("admin"));
        home.openDemoDashBoard();
        assertTrue(home.isDemoInfoBlockDisplayed());
        home.clickLogo();
        home.logout();
    }
}
