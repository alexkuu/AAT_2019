package selenium.pageObjects;

import Interfaces.pageObjects.HomePage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import service.ui.DriverManager;
import service.ui.MiscActions;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HomePageSelenium implements HomePage {

    @FindBy(how = How.CLASS_NAME, using = "dashboard-list-view-table")
    public WebElement dashboardsList;

    @FindBy(how = How.ID, using = "profileImage")
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
    public WebElement confirmDeleteBtn;

    public HomePageSelenium() {
    }

    @Override
    public void logout() {
        MiscActions.jsClick(profileExpander);
        MiscActions.waitUntilElementIsExists(By.xpath("//ul[@id='userNavigator']//span[contains(.,'Logout')]"));
        MiscActions.waitUntilClickable(By.xpath("//ul[@id='userNavigator']//span[contains(.,'Logout')]"));
        MiscActions.jsClick(logoutLink);
        MiscActions.waitUntilElementIsExists(By.xpath("//input[@placeholder='Login']"));
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
        MiscActions.waitUntilElementIsExists(By.xpath("//input[@placeholder='Enter dashboard name']"));
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
        MiscActions.jsClick(deleteBtn);
        MiscActions.waitUntilElementIsExists(By.xpath("//button[contains(.,'Delete')]"));
        confirmDeleteBtn.click();
        MiscActions.waitUntilElementIsExists(By.xpath("//*[contains(.,'Dashboard has been deleted!')]"));
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
