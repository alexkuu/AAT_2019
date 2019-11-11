package selenide.pageObjects;

import Interfaces.pageObjects.HomePage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import service.ui.DriverManager;

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
        $(byText("Dashboard has been added")).shouldBe(Condition.visible);
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
}
