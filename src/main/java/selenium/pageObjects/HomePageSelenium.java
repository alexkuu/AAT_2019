package selenium.pageObjects;

import Interfaces.pageObjects.HomePage;
import constants.Classes;
import constants.Ids;
import constants.Xpathes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import service.ui.DriverManager;
import service.ui.MiscActions;
import service.ui.Widget;

public class HomePageSelenium implements HomePage {

    @FindBy(how = How.CLASS_NAME, using = Classes.DASHBOARD_LIST)
    private WebElement dashboardsList;

    @FindBy(how = How.ID, using = Ids.PROFILE_IMAGE)
    private WebElement profileExpander;

    @FindBy(how = How.XPATH, using = Xpathes.LOGOUT_LINK)
    private WebElement logoutLink;

    @FindBy(how = How.XPATH, using = Xpathes.SETTINGS_LINK)
    private WebElement settingsLink;

    @FindBy(how = How.CLASS_NAME, using = Classes.LOGO)
    private WebElement logo;

    @FindBy(how = How.XPATH, using = Xpathes.ADD_NEW_DASHBOARD)
    private WebElement addNewDashboardBtn;

    @FindBy(how = How.XPATH, using = Xpathes.NEW_DASHBOARD_NAME)
    private WebElement newDashboardName;

    @FindBy(how = How.XPATH, using = Xpathes.NEW_DASHBOARD_SAVE_BUTTON)
    private WebElement addBtn;

    @FindBy(how = How.XPATH, using = Xpathes.DASHBOARD_CONFIRM_DELETE_BTN)
    private WebElement confirmDeleteBtn;

    public HomePageSelenium() {
    }

    @Override
    public void logout() {
        MiscActions.jsClick(profileExpander);
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.LOGOUT_LINK));
        MiscActions.waitUntilClickable(By.xpath(Xpathes.LOGOUT_LINK));
        MiscActions.jsClick(logoutLink);
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.LOGIN_INPUT));
    }

    @Override
    public void clickLogo() {
        logo.click();
    }

    @Override
    public void openSettings() {
        settingsLink.click();
    }

    @Override
    public void clickAddNewDashBoard() {
        addNewDashboardBtn.click();
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.NEW_DASHBOARD_NAME));
    }

    @Override
    public void inputNewDashboardName(String name) {
        newDashboardName.clear();
        newDashboardName.sendKeys(name);
    }

    @Override
    public void clickAddDashBoard() {
        addBtn.click();
    }

    @Override
    public void deleteDashboard(String name) {
        WebElement deleteBtn = DriverManager.getDriver().findElement(By.xpath(Xpathes.DASHBOARD_DELETE_ICON(name)));
        MiscActions.jsClick(deleteBtn);
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.DASHBOARD_CONFIRM_DELETE_BTN));
        confirmDeleteBtn.click();
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.DASHBOARD_DELETED_MESSAGE));
    }

    @Override
    public boolean isDashboardDisplayed(String name) {
        return DriverManager.getDriver().findElement(By.xpath(Xpathes.DASHBOARD_BY_NAME(name))).isDisplayed();
    }

    @Override
    public boolean isDashBoardListDisplayed() {
        return dashboardsList.isDisplayed();
    }

    @Override
    public void openDemoDashboard() {
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.DEMO_DASHBOARD)).click();
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.DEMO_DASHBOARD_NAME_IN_BREADCRUMB));
    }

    @Override
    public void openDashboard(String name) {
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.DASHBOARD_BY_NAME_FOR_OPEN(name))).click();
        MiscActions.waitUntilElementIsExists(By.xpath(Xpathes.DASHBOARD_IN_BREADCRUMB(name)));
    }

    @Override
    public void dragNDropDemoChartByOffset(int x, int y) {
        MiscActions.dragNDropByOffset(Widget.getTopWidgetMover(), x, y);
    }

    @Override
    public boolean demoChartOnTheRightSide() {
        return Widget.getTopWidgetXPos() == 6;
    }

    @Override
    public boolean demoChartOnTheLeftSide() {
        return Widget.getTopWidgetXPos() == 0;
    }

    @Override
    public void increaseDemoChartHeight() {
        MiscActions.hoverElement1DragElement2(By.xpath(Widget.getTopWidgetMainXpath()), By.xpath(Widget.getTopWidgetResizerXpath()), 0, 80);
    }

    @Override
    public void decreaseDemoChartHeight() {
        MiscActions.hoverElement1DragElement2(By.xpath(Widget.getTopWidgetMainXpath()), By.xpath(Widget.getTopWidgetResizerXpath()), 0, -80);
    }

    @Override
    public int getDemoChartHeight() {
        return Widget.getTopWidgetHeight();
    }

    @Override
    public int getDemoChartWidth() {
        return Widget.getTopWidgetWidth();
    }

    @Override
    public void increaseDemoChartWidth(int x) {
        MiscActions.hoverElement1DragElement2(By.xpath(Widget.getTopWidgetMainXpath()), By.xpath(Widget.getTopWidgetResizerXpath()), x, 0);
    }
}
