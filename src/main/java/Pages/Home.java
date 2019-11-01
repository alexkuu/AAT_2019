package Pages;

import PageObjects.HomePage;
import PageObjects.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import service.ui.DriverManager;

public class Home extends BasePage{


    private HomePage obj;

    public Home(){
        obj = new Pages().getHomePage();
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
        logger.info("Dashboard has been added ");
    }

    public boolean isDashBoardDisplayed(String name){
        return DriverManager.getDriver().findElement(By.xpath("//div[@class='dashboard-container']//p[text()='" + name + "']")).isDisplayed();
    }

    public boolean isDashBoardNotDisplayed(String name){
        return DriverManager.getDriver().findElements(By.xpath("//div[@class='dashboard-container']//p[text()='" + name + "']")).size() == 0;
    }

    public void deleteDashBoard(String name){
        WebElement deleteBtn = DriverManager.getDriver().findElement(By.xpath("//p[text()='" + name + "']/following-sibling::div/i[text()='close']"));
        jsClick(deleteBtn);
        obj.deleteBtn.click();
    }
}
