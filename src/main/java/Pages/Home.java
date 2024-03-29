package Pages;

import Interfaces.pageObjects.HomePage;
import PageObjects.Pages;
import constants.Ids;
import constants.Xpathes;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import service.ui.DriverManager;
import service.ui.MiscActions;
import service.ui.Widget;

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

    public void openDemoDashboard() {
        obj.openDemoDashboard();
    }

    public void openDashboard(String name) {
        obj.openDashboard(name);
    }

    public void dragNDropDemoChartByOffset(int x, int y) {
        obj.dragNDropDemoChartByOffset(x, y);
    }

    public boolean demoChartOnTheRightSide() {
        return obj.demoChartOnTheRightSide();
    }

    public boolean demoChartOnTheLeftSide() {
        return obj.demoChartOnTheLeftSide();
    }

    public int getDemoChartHeight() {
        return obj.getDemoChartHeight();
    }

    public int getDemoChartWidth() {
        return obj.getDemoChartWidth();
    }

    public void increaseDemoChartHeight() {
        obj.increaseDemoChartHeight();
    }

    public void decreaseDemoChartHeight() {
        obj.decreaseDemoChartHeight();
    }

    public void increaseDemoChartWidth(int x) {
        obj.increaseDemoChartWidth(x);
    }

    //Test method
    public void checkGreyZoneDuringResize() {
        try {
            MiscActions.changeImplicitWait(3);

            // First check that placeholder not exists
            Assert.assertEquals(DriverManager.getDriver().findElements(By.xpath(Xpathes.GRAY_SQUARE_PROPOSAL)).size(), 0);
            MiscActions.changeImplicitWait(30);

            new Actions(DriverManager.getDriver()).moveToElement(Widget.getTopWidgetElement())
                    .moveToElement(Widget.getTopWidgetSizer())
                    .clickAndHold()
                    .moveByOffset(-30, 0)
                    .build()
                    .perform();

            // Check that placeholder exists
            Assert.assertEquals(DriverManager.getDriver().findElements(By.xpath(Xpathes.GRAY_SQUARE_PROPOSAL)).size(), 1);

            new Actions(DriverManager.getDriver()).release().build().perform();
        } finally {
            MiscActions.changeImplicitWait(30);
        }
    }
    //Test method
    public void checkGreyZoneDuringMove() {
        try {
            MiscActions.changeImplicitWait(3);

            // First check that placeholder not exists
            Assert.assertEquals(DriverManager.getDriver().findElements(By.xpath(Xpathes.GRAY_SQUARE_PROPOSAL)).size(), 0);
            MiscActions.changeImplicitWait(30);

            new Actions(DriverManager.getDriver()).moveToElement(Widget.getTopWidgetElement())
                    .moveToElement(Widget.getTopWidgetSizer())
                    .clickAndHold()
                    .moveByOffset(0, 100)
                    .build()
                    .perform();

            // Check that placeholder exists
            Assert.assertEquals(DriverManager.getDriver().findElements(By.xpath(Xpathes.GRAY_SQUARE_PROPOSAL)).size(), 1);

            new Actions(DriverManager.getDriver()).release().build().perform();
        } finally {
            MiscActions.changeImplicitWait(30);
        }
    }

    //Test method
    public void moveBottomWidgetToTheTop() {
        while(Widget.getBottomWidgetYPos() > 0){
            MiscActions.dragNDropByOffset(Widget.getBottomWidgetMover(), 0, -300);
            MiscActions.waitPageToLoad();
            MiscActions.scrollTo(Widget.getBottomWidgetElement());
            MiscActions.waitPageToLoad();
        }
    }

    //Test method
    public void moveBottomWidgetToTheBottom() {
        MiscActions.scrollTo(Widget.getBottomWidgetElement());
        while(Widget.getBottomWidgetYPos() < 12){
            new Actions(DriverManager.getDriver()).moveToElement(Widget.getBottomWidgetMover())
                    .clickAndHold()
                    .moveToElement(DriverManager.getDriver().findElement(By.id(Ids.PAGE_FOOTER)))
                    .release()
                    .build()
                    .perform();
            MiscActions.waitPageToLoad();
            MiscActions.scrollTo(Widget.getBottomWidgetElement());
            MiscActions.waitPageToLoad();
        }
    }
}
