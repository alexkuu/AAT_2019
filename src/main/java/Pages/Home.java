package Pages;

import PageObjects.HomePage;
import PageObjects.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import service.ui.Driver;

public class Home extends BasePage{


    static HomePage obj;

    public Home(){
        obj = Pages.getHomePage();
    }

    public boolean isDashBoardsListDisplayed(){
        return obj.dashboardsList.isDisplayed();
    }

    public void logout(){
        obj.profileExpander.click();
        obj.logoutLink.click();
    }

    public void clickLogo(){
        obj.logo.click();
    }

    public void openSettings(){
        obj.settingsLink.click();
    }

    public void addNewDashBoard(String name){
        obj.addNewDashboardBtn.click();
        obj.newDashboardName.clear();
        obj.newDashboardName.sendKeys(name);
        obj.addBtn.click();
    }

    public boolean isDashBoardDisplayed(String name){
        return Driver.getDriver().findElement(By.xpath("//div[@class='dashboard-container']//p[text()='" + name + "']")).isDisplayed();
    }

    public boolean isDashBoardNotDisplayed(String name){
        return Driver.getDriver().findElements(By.xpath("//div[@class='dashboard-container']//p[text()='" + name + "']")).size() == 0;
    }

    public void deleteDashBoard(String name){
        WebElement deleteBtn = Driver.getDriver().findElement(By.xpath("//p[text()='" + name + "']/following-sibling::div/i[text()='close']"));
        jsClick(deleteBtn);
        obj.deleteBtn.click();
    }
}
