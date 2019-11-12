package uiTests;

import Pages.Home;
import Pages.Login;
import Pages.Settings;
import dataProvider.SmokeDataProvider;
import javafx.geometry.Pos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.Config;
import service.ui.DriverManager;
import service.ui.MiscActions;
import service.ui.Widget;

import static service.UserFactory.getUser;

public class SmokeTests extends BaseTest {

    @Test(dataProvider = "users", dataProviderClass = SmokeDataProvider.class)
    public void loginSuccess(String userRole) {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(userRole);
        Assert.assertTrue(home.isDashBoardsListDisplayed());
        home.logout();
    }

    @Test
    public void loginFailed() {
        Login login = new Login();
        login.openHomePage();
        login.login(getUser("user").getName(), "wronG_PassW0RD");
        Assert.assertTrue(login.isErrorMessageDisplayed());
    }

    @Test
    public void checkSettings() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openSettings();
        Assert.assertTrue(new Settings().isSettingsPageDisplayed());
        home.clickLogo();
        home.logout();
    }

    @Test
    public void dashBoardAddEditDelete() {
        String dashBoardName = "dash_" + (int) (Math.random() * 9999999) + 1;
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.addNewDashBoard(dashBoardName);
        Assert.assertTrue(home.isDashBoardDisplayed(dashBoardName));
        home.deleteDashBoard(dashBoardName);
        home.logout();
    }

    @Test
    public void dragNDropDemoChart() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        Assert.assertTrue(home.demoChartOnTheLeftSide());
        home.dragNDropDemoChartByOffset(700, 0);
        Assert.assertTrue(home.demoChartOnTheRightSide());
        home.dragNDropDemoChartByOffset(-700, 0);
        Assert.assertTrue(home.demoChartOnTheLeftSide());
        home.logout();
    }

    @Test
    public void resizeDemoChart() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        int initialHeight = home.getDemoChartHeight();
        home.increaseDemoChartHeight();
        Assert.assertEquals(home.getDemoChartHeight(), initialHeight + 1);
        home.decreaseDemoChartHeight();
        Assert.assertEquals(home.getDemoChartHeight(), initialHeight);
        home.logout();
    }

    @Test
    public void resizeDemoChart_2() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        int initialHeight = home.getDemoChartHeight();
        home.increaseDemoChartHeight();
        home.clickLogo();
        home.openDemoDashboard();
        Assert.assertEquals(home.getDemoChartHeight(), initialHeight + 1);
        home.decreaseDemoChartHeight();
        home.clickLogo();
        home.openDemoDashboard();
        Assert.assertEquals(home.getDemoChartHeight(), initialHeight);
        home.logout();
    }

    @Test
    public void otherWidgetsMovingWhileResizing_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        WebElement bottomWidget = DriverManager.getDriver().findElement(By.xpath("//div[@data-id='5dc938fb9daec200016cdbb0']"));
        int bottomWidgetStartYPos = Integer.valueOf(bottomWidget.getAttribute("data-gs-y"));
        home.increaseDemoChartHeight();
        Assert.assertEquals((int) Integer.valueOf(bottomWidget.getAttribute("data-gs-y")), bottomWidgetStartYPos + 1);
        home.decreaseDemoChartHeight();
        Assert.assertEquals((int) Integer.valueOf(bottomWidget.getAttribute("data-gs-y")), bottomWidgetStartYPos);
        home.logout();
    }

    @Test
    public void user_cannot_make_element_wider_than_dashboard_itself_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        home.increaseDemoChartWidth(700);
        int width = home.getDemoChartWidth();
        home.increaseDemoChartWidth(100);
        Assert.assertEquals(width, home.getDemoChartWidth());
        home.increaseDemoChartWidth(-700);
        home.logout();
    }

    @Test
    public void content_of_widget_resizes_as_well_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        WebElement svg = DriverManager.getDriver().findElement(By.xpath(Widget.getTopWidgetMainXpath() + "//*[local-name() = 'svg']"));
        double initWidth = Double.valueOf(svg.getAttribute("width"));
        home.increaseDemoChartWidth(700);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        Assert.assertTrue(Double.valueOf(svg.getAttribute("width")) > initWidth);
        home.increaseDemoChartWidth(-700);
        home.logout();
    }

    @Test
    public void dashboard_proposes_the_new_size_using_gray_square_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        home.checkGreyZoneDuringResize();
        home.logout();
    }

    @Test
    public void shouldFailWithScreenshot() throws Exception {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        throw new Exception("Explicit fail");
    }

    @Test
    public void scrollTo_elementIsOnView_waitJsLoad_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        MiscActions.waitPageToLoad();
        Assert.assertFalse(MiscActions.elementIsOnView(Widget.getBottomWidgetElement()));
        Assert.assertTrue(MiscActions.elementIsOnView(Widget.getTopWidgetElement()));
        MiscActions.scrollTo(Widget.getBottomWidgetElement());
        MiscActions.waitPageToLoad();
        Assert.assertTrue(MiscActions.elementIsOnView(Widget.getBottomWidgetElement()));
        Assert.assertFalse(MiscActions.elementIsOnView(Widget.getTopWidgetElement()));
        MiscActions.scrollTo(Widget.getTopWidgetElement());
        MiscActions.waitPageToLoad();
        Assert.assertFalse(MiscActions.elementIsOnView(Widget.getBottomWidgetElement()));
        Assert.assertTrue(MiscActions.elementIsOnView(Widget.getTopWidgetElement()));
        home.logout();
    }

    @Test
    public void move_bottom_widget_to_the_top_and_back_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        MiscActions.waitPageToLoad();
        Assert.assertEquals(Widget.getTopWidgetYPos(), 0); // Verify other widgets moves as well
        Assert.assertFalse(MiscActions.elementIsOnView(Widget.getBottomWidgetElement()));
        MiscActions.scrollTo(Widget.getBottomWidgetElement());
        MiscActions.waitPageToLoad();
        Assert.assertTrue(MiscActions.elementIsOnView(Widget.getBottomWidgetElement()));
        home.moveBottomWidgetToTheTop();
        home.clickLogo();
        home.openDemoDashboard();
        Assert.assertNotEquals(Widget.getTopWidgetYPos(), 0); // Verify other widgets moves as well
        Assert.assertTrue(MiscActions.elementIsOnView(Widget.getBottomWidgetElement()));
        home.moveBottomWidgetToTheBottom();
        home.clickLogo();
        home.openDemoDashboard();
        Assert.assertEquals(Widget.getTopWidgetYPos(), 0); // Verify other widgets moves as well
        Assert.assertFalse(MiscActions.elementIsOnView(Widget.getBottomWidgetElement()));
        home.logout();
    }

    @Test
    public void dashboard_proposes_the_new_position_using_gray_square_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        MiscActions.waitPageToLoad();
        home.checkGreyZoneDuringMove();
        home.logout();
    }

    @Test
    public void verify_content_scrolling_independently_from_page_for_widget_OVERALL_STATISTICS_PANEL_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login("user");
        home.openDemoDashboard();
        MiscActions.waitPageToLoad();
        MiscActions.scrollTo(Widget.getOverallStatisticsWidgetElement());
        MiscActions.waitPageToLoad();
        String initRightPartTopPosition = DriverManager.getDriver().findElement(By.xpath(Widget.getOSRightPartScrollerXpath())).getCssValue("top");
        MiscActions.hoverElement1DragElement2(By.xpath(Widget.getOSLeftPartXpath()), By.xpath(Widget.getOSLeftPartScrollerXpath()), 0, 50);
        String currentRightPartTopPosition = DriverManager.getDriver().findElement(By.xpath(Widget.getOSRightPartScrollerXpath())).getCssValue("top");
        Assert.assertEquals(initRightPartTopPosition, currentRightPartTopPosition);
        home.logout();
    }
}
