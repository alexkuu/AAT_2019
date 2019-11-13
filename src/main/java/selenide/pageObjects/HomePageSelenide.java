package selenide.pageObjects;

import Interfaces.pageObjects.HomePage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import service.ui.MiscActions;
import service.ui.Widget;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePageSelenide implements HomePage {
    @Override
    public void logout() {
        $("#profileImage").click();
        $(byText("Logout")).shouldBe(Condition.visible);
        $(byText("Logout")).click();
        $(byText("Login")).shouldBe(Condition.visible);
    }

    @Override
    public void clickLogo() {
        $(".logo").click();
    }

    @Override
    public void openSettings() {
        $(byText("settings")).click();
    }

    @Override
    public void clickAddNewDashBoard() {
        $(byText("Add new dashboard")).click();
        $(byAttribute("placeholder", "Enter dashboard name")).shouldBe(Condition.visible);
    }

    @Override
    public void inputNewDashboardName(String name) {
        $(byAttribute("placeholder", "Enter dashboard name")).sendKeys(name);
    }

    @Override
    public void clickAddDashBoard() {
        $(byXpath("//div[@class='modal-footer']//button[contains(.,'Add')]")).click();
        $(byText("Dashboard has been added")).waitUntil(Condition.visible, 10000);
    }

    @Override
    public void deleteDashboard(String name) {
        $(byXpath("//div[@class='dashboard-container']//p[text()='" + name + "']")).hover();
        $(byXpath("//div[@class='dashboard-container']//p[text()='" + name + "']/following-sibling::div/i[text()='close']")).click();
        $(byText("Delete")).shouldBe(Condition.visible).click();
        $(byText("Dashboard has been deleted!")).shouldBe(Condition.visible);
    }

    @Override
    public boolean isDashboardDisplayed(String name) {
        return $(byXpath("//div[@class='dashboard-container']//p[text()='" + name + "']")).isDisplayed();
    }

    @Override
    public boolean isDashBoardListDisplayed() {
        return $(".dashboard-list-view-table").isDisplayed();
    }

    @Override
    public void openDemoDashboard() {
        $(byXpath("//div[@class='dashboard-content'][div[@class='name-wrapper']/p[text()='DEMO DASHBOARD#_demo']]")).shouldBe(Condition.visible).click();
        $(byXpath("//ul[@class='main-breadcrumbs']//span[text()='DEMO DASHBOARD#_demo']")).shouldBe(Condition.visible);
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
