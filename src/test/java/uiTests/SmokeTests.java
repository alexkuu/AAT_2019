package uiTests;

import Pages.Home;
import Pages.Login;
import Pages.Settings;
import dataProvider.SmokeDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.Config;

public class SmokeTests extends BaseTest {

    @Test(dataProvider = "users", dataProviderClass = SmokeDataProvider.class)
    public void loginSuccess(String userName, String password){
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(userName, password);
        Assert.assertTrue(home.isDashBoardsListDisplayed());
        home.logout();
    }

    @Test
    public void loginFailed(){
        Login login = new Login();
        login.openHomePage();
        login.login(Config.getUserName(), "wronG_PassW0RD");
        Assert.assertTrue(login.isErrorMessageDisplayed());
    }

    @Test
    public void checkSettings(){
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(Config.getUserName(), Config.getUserPassword());
        home.openSettings();
        Assert.assertTrue(new Settings().isSettingsPageDisplayed());
        home.clickLogo();
        home.logout();
    }

    @Test
    public void wDashBoardAddEditDelete(){
        String dashBoardName = "dash_" + Math.random();
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(Config.getUserName(), Config.getUserPassword());
        home.addNewDashBoard(dashBoardName);
        Assert.assertTrue(home.isDashBoardDisplayed(dashBoardName));
        home.deleteDashBoard(dashBoardName);
        Assert.assertTrue(home.isDashBoardNotDisplayed(dashBoardName));
        home.logout();
    }
}
