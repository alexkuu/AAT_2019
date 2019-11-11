package selenide.pageObjects;

import Interfaces.pageObjects.HomePage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import service.ui.DriverManager;
import service.ui.MiscActions;

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
        $(byXpath("//div[@class='dashboard-content'][div[@class='name-wrapper']/p[text()='DEMO DASHBOARD#demo']]")).shouldBe(Condition.visible).click();
        $(byXpath("//ul[@class='main-breadcrumbs']//span[text()='DEMO DASHBOARD#demo']")).shouldBe(Condition.visible);
    }

    @Override
    public void dragNDropDemoChartByOffset(int x, int y) {
        MiscActions.dragNDropByOffset(By.xpath("//div[@data-id='5dc938fb9daec200016cdbae']//div[@class='gadget-header ui-draggable-handle']"), x, y);
    }

    @Override
    public boolean demoChartOnTheRightSide() {
        return $(byXpath("//div[@data-id='5dc938fb9daec200016cdbae']")).getAttribute("data-gs-x").equals("6");
    }

    @Override
    public boolean demoChartOnTheLeftSide() {
        return $(byXpath("//div[@data-id='5dc938fb9daec200016cdbae']")).getAttribute("data-gs-x").equals("0");
    }

    @Override
    public void increaseDemoChartHeight() {
        $(byXpath("//div[@data-id='5dc938fb9daec200016cdbae']")).hover();
        MiscActions.dragNDropByOffset(By.xpath("//div[@data-id='5dc938fb9daec200016cdbae']//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"), 0, 80);
    }

    @Override
    public void decreaseDemoChartHeight() {
        $(byXpath("//div[@data-id='5dc938fb9daec200016cdbae']")).hover();
        MiscActions.dragNDropByOffset(By.xpath("//div[@data-id='5dc938fb9daec200016cdbae']//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"), 0, -80);
    }

    @Override
    public int getDemoChartHeight() {
        return Integer.valueOf($(byXpath("//div[@data-id='5dc938fb9daec200016cdbae']")).getAttribute("data-gs-height"));
    }

    @Override
    public int getDemoChartWidth() {
        return Integer.valueOf(DriverManager.getDriver().findElement(By.xpath("//div[@data-id='5dc938fb9daec200016cdbae']")).getAttribute("data-gs-width"));
    }

    @Override
    public void increaseDemoChartWidth(int x) {
        MiscActions.hoverElement1DragElement2(By.xpath("//div[@data-id='5dc938fb9daec200016cdbae']"), By.xpath("//div[@data-id='5dc938fb9daec200016cdbae']//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"), x, 0);

    }

}
