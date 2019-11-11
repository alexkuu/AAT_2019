package Pages;

import Interfaces.pageObjects.HomePage;
import PageObjects.Pages;

public class Home extends BasePage {


    static HomePage obj;

    public Home() {
        obj = new Pages().getHomePage();
    }

    public boolean isDashBoardsListDisplayed() {
        return obj.isDashBoardListDisplayed();
    }

    public void logout() {
        obj.logout();
    }

    public void clickLogo() {
        obj.clickLogo();
    }

    public void openSettings() {
        obj.openSettings();
    }

    public void addNewDashBoard(String name) {
        clickAddNewDashBoard();
        typeNewDashBoardName(name);
        clickAddDashBoard();
    }

    private void clickAddNewDashBoard() {
        obj.clickAddNewDashBoard();
    }

    private void typeNewDashBoardName(String name) {
        obj.inputNewDashboardName(name);
    }

    private void clickAddDashBoard() {
        obj.clickAddDashBoard();
    }

    public boolean isDashBoardDisplayed(String name) {
        return obj.isDashboardDisplayed(name);
    }

    public void deleteDashBoard(String name) {
        obj.deleteDashboard(name);
    }
}
