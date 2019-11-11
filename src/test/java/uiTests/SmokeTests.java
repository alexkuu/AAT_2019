package uiTests;

import Pages.Home;
import Pages.Login;
import Pages.Settings;
import dataProvider.SmokeDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import static service.UserFactory.getUser;

public class SmokeTests extends BaseTest {

    @Test(dataProvider = "users", dataProviderClass = SmokeDataProvider.class)
    public void loginSuccess(String userRole){
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(userRole);
        Assert.assertTrue(home.isDashBoardsListDisplayed());
        home.logout();
    }

    @Test
    public void loginFailed(){
        Login login = new Login();
        login.openHomePage();
        login.login(getUser("user").getName(), "wronG_PassW0RD");
        Assert.assertTrue(login.isErrorMessageDisplayed());
    }

    @Test
    public void checkSettings(){
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openSettings();
        Assert.assertTrue(new Settings().isSettingsPageDisplayed());
        home.clickLogo();
        home.logout();
    }

    @Test
    public void dashBoardAddEditDelete(){
        String dashBoardName = "dash_" + (int)(Math.random() * 9999999) + 1;
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.addNewDashBoard(dashBoardName);
        Assert.assertTrue(home.isDashBoardDisplayed(dashBoardName));
        home.deleteDashBoard(dashBoardName);
        home.logout();
    }
}
