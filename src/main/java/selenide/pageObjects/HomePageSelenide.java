package selenide.pageObjects;

import Interfaces.pageObjects.HomePage;
import com.codeborne.selenide.Condition;
import constants.AppTexts;
import constants.Classes;
import constants.Ids;
import constants.Xpathes;
import org.openqa.selenium.By;
import service.ui.MiscActions;
import service.ui.Widget;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePageSelenide implements HomePage {
    @Override
    public void logout() {
        $(byId(Ids.PROFILE_IMAGE)).click();
        $(byText(AppTexts.LOGOUT)).shouldBe(Condition.visible);
        $(byText(AppTexts.LOGOUT)).click();
        $(byText(AppTexts.LOGIN)).shouldBe(Condition.visible);
    }

    @Override
    public void clickLogo() {
        $(byClassName(Classes.LOGO)).click();
    }

    @Override
    public void openSettings() {
        $(byText(AppTexts.SETTINGS)).click();
    }

    @Override
    public void clickAddNewDashBoard() {
        $(byText(AppTexts.ADD_NEW_DASHBOARD)).click();
        $(byAttribute("placeholder", AppTexts.ENTER_DASHBOARD_NAME)).shouldBe(Condition.visible);
    }

    @Override
    public void inputNewDashboardName(String name) {
        $(byAttribute("placeholder", AppTexts.ENTER_DASHBOARD_NAME)).sendKeys(name);
    }

    @Override
    public void clickAddDashBoard() {
        $(byXpath(Xpathes.NEW_DASHBOARD_SAVE_BUTTON)).click();
        $(byText(AppTexts.DASHBOARD_ADDED)).waitUntil(Condition.visible, 10000);
    }

    @Override
    public void deleteDashboard(String name) {
        $(byXpath(Xpathes.DASHBOARD_BY_NAME(name))).hover();
        $(byXpath(Xpathes.DASHBOARD_DELETE_ICON(name))).click();
        $(byText(AppTexts.DELETE)).shouldBe(Condition.visible).click();
        $(byText(AppTexts.DASHBOARD_DELETED)).shouldBe(Condition.visible);
    }

    @Override
    public boolean isDashboardDisplayed(String name) {
        return $(byXpath(Xpathes.DASHBOARD_BY_NAME(name))).isDisplayed();
    }

    @Override
    public boolean isDashBoardListDisplayed() {
        return $(byClassName(Classes.DASHBOARD_LIST)).isDisplayed();
    }

    @Override
    public void openDemoDashboard() {
        $(byXpath(Xpathes.DEMO_DASHBOARD)).shouldBe(Condition.visible).click();
        $(byXpath(Xpathes.DEMO_DASHBOARD_NAME_IN_BREADCRUMB)).shouldBe(Condition.visible);
    }

    @Override
    public void openDashboard(String name) {
        $(byXpath(Xpathes.DASHBOARD_BY_NAME_FOR_OPEN(name))).shouldBe(Condition.visible).click();
        $(byXpath(Xpathes.DASHBOARD_IN_BREADCRUMB(name))).shouldBe(Condition.visible);
    }

    @Override
    public void dragNDropDemoChartByOffset(int x, int y) {
        MiscActions.dragNDropByOffset(By.xpath(Widget.getTopWidgetMoverXpath()), x, y);
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
        $(byXpath(Widget.getTopWidgetMainXpath())).hover();
        MiscActions.dragNDropByOffset(By.xpath(Widget.getTopWidgetResizerXpath()), 0, 80);
    }

    @Override
    public void decreaseDemoChartHeight() {
        $(byXpath(Widget.getTopWidgetMainXpath())).hover();
        MiscActions.dragNDropByOffset(By.xpath(Widget.getTopWidgetResizerXpath()), 0, -80);
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
