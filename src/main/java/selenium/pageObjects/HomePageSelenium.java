package selenium.pageObjects;

import Interfaces.pageObjects.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import service.ui.DriverManager;

public class HomePageSelenium implements HomePage{

    @FindBy(how = How.CLASS_NAME, using = "dashboard-list-view-table")
    public WebElement dashboardsList;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'rp-user-navigator')]/i[@class='material-icons arrow_drop_down']")
    public WebElement profileExpander;

    @FindBy(how = How.XPATH, using = "//ul[@id='userNavigator']//span[contains(.,'Logout')]")
    public WebElement logoutLink;

    @FindBy(how = How.XPATH, using = "//li[@id='settings']//i")
    public WebElement settingsLink;

    @FindBy(how = How.CLASS_NAME, using = "logo")
    public WebElement logo;

    @FindBy(how = How.XPATH, using = "//span[text()='Add new dashboard']")
    public WebElement addNewDashboardBtn;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Enter dashboard name']")
    public WebElement newDashboardName;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-footer']//button[contains(.,'Add')]")
    public WebElement addBtn;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Delete')]")
    public WebElement deleteBtn;

    public HomePageSelenium() {
    }

    @Override
    public void logout() {
        profileExpander.click();
        logoutLink.click();
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
        WebElement deleteBtn = DriverManager.getDriver().findElement(By.xpath("//p[text()='" + name + "']/following-sibling::div/i[text()='close']"));
        deleteBtn.click();
    }

    @Override
    public boolean isDashboardDisplayed(String name) {
        return DriverManager.getDriver().findElement(By.xpath("//div[@class='dashboard-container']//p[text()='" + name + "']")).isDisplayed();
    }

    @Override
    public boolean isDashBoardListDisplayed() {
        return dashboardsList.isDisplayed();
    }
}
