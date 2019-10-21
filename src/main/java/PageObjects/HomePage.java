package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

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

    public HomePage(){
    }
}
