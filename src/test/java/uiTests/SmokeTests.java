package uiTests;

import Enums.Users;
import Pages.Home;
import Pages.Login;
import Pages.Settings;
import constants.Xpathes;
import dataProvider.SmokeDataProvider;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import service.api.ApiClient;
import service.ui.Widget;
import utils.Utils;

import static service.UserFactory.getUser;
import static service.assertions.Assertion.*;
import static service.ui.MiscActions.*;

public class SmokeTests extends BaseTest {

    @Test(dataProvider = "users", dataProviderClass = SmokeDataProvider.class)
    public void loginSuccess(Users user) {
        Login login = new Login();
        Home home = new Home();
        login.openHomePage();
        login.login(user);
        assertTrue(home.isDashBoardsListDisplayed());
        home.logout();
    }

    @Test(dataProvider = "users", dataProviderClass = SmokeDataProvider.class)
    public void loginSuccessWithApiLogin(Users user) {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(user);
        assertTrue(home.isDashBoardsListDisplayed());
        login.logoutWithApi();
    }

    @Test
    public void loginFailed() {
        Login login = new Login();
        login.openHomePage();
        login.login(getUser(Users.USER.toString().toLowerCase()).getName(), "wronG_PassW0RD");
        assertTrue(login.isErrorMessageDisplayed());
    }

    @Test
    public void checkSettings() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openSettings();
        assertTrue(new Settings().isSettingsPageDisplayed());
        home.clickLogo();
        login.logoutWithApi();
    }

    @Test
    public void dashBoardAddEditDelete() {
        String dashBoardName = "dash_" + (int) (Math.random() * 9999999) + 1;
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.addNewDashBoard(dashBoardName);
        assertTrue(home.isDashBoardDisplayed(dashBoardName));
        home.deleteDashBoard(dashBoardName);
        login.logoutWithApi();
    }

    @Test
    public void hybrid_dashBoardAddEditDelete_1() {
        String dashBoardName = "dash_" + Utils.genRandomString();
        Login login = new Login();
        Home home = new Home();
        ApiClient api = new ApiClient(Users.USER);
        api.dashboard.create("default_personal", dashBoardName);
        login.loginWithApi(api);
        assertTrue(home.isDashBoardDisplayed(dashBoardName));
        home.deleteDashBoard(dashBoardName);
        login.logoutWithApi(api);
    }

    @Test
    public void hybrid_addDashBoardAndWidget() {
        Login login = new Login();
        Home home = new Home();
        String project = "superadmin_personal";
        String dashBoardName = "dash_" + Utils.genRandomString();
        String widgetName = "widg_" + Utils.genRandomString();
        ApiClient api = new ApiClient(Users.ADMIN);

        String dashboardId = new JSONObject(api.dashboard.create(project, dashBoardName)).getString("id");
        String widgetId = new JSONObject(api.widget.createSimpleWidget(project, widgetName)).getString("id");
        api.widget.addToDashboard(project, dashboardId, widgetId);

        login.loginWithApi(api);
        assertTrue(home.isDashBoardDisplayed(dashBoardName));
        home.openDashboard(dashBoardName);
        assertTrue(Widget.widgetExistByName(widgetName));

        api.widget.delete(project, dashboardId, widgetId);
        api.dashboard.delete(project, dashboardId);
        login.logoutWithApi(api);
    }

    @Test
    public void dragNDropDemoChart() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        assertTrue(home.demoChartOnTheLeftSide());
        home.dragNDropDemoChartByOffset(700, 0);
        assertTrue(home.demoChartOnTheRightSide());
        home.dragNDropDemoChartByOffset(-700, 0);
        assertTrue(home.demoChartOnTheLeftSide());
        login.logoutWithApi();
    }

    @Test
    public void resizeDemoChart() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        int initialHeight = home.getDemoChartHeight();
        home.increaseDemoChartHeight();
        assertEquals(home.getDemoChartHeight(), initialHeight + 1);
        home.decreaseDemoChartHeight();
        assertEquals(home.getDemoChartHeight(), initialHeight);
        login.logoutWithApi();
    }

    @Test
    public void resizeDemoChart_2() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        int initialHeight = home.getDemoChartHeight();
        home.increaseDemoChartHeight();
        home.clickLogo();
        home.openDemoDashboard();
        assertEquals(home.getDemoChartHeight(), initialHeight + 1);
        home.decreaseDemoChartHeight();
        home.clickLogo();
        home.openDemoDashboard();
        assertEquals(home.getDemoChartHeight(), initialHeight);
        login.logoutWithApi();
    }

    @Test
    public void otherWidgetsMovingWhileResizing_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        int bottomWidgetStartYPos = Widget.getBottomWidgetYPos();
        home.increaseDemoChartHeight();
        assertEquals(Widget.getBottomWidgetYPos(), bottomWidgetStartYPos + 1);
        home.decreaseDemoChartHeight();
        assertEquals(Widget.getBottomWidgetYPos(), bottomWidgetStartYPos);
        login.logoutWithApi();
    }

    @Test
    public void user_cannot_make_element_wider_than_dashboard_itself_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        home.increaseDemoChartWidth(700);
        int width = home.getDemoChartWidth();
        home.increaseDemoChartWidth(100);
        assertEquals(width, home.getDemoChartWidth());
        home.increaseDemoChartWidth(-700);
        login.logoutWithApi();
    }

    @Test
    public void content_of_widget_resizes_as_well_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        WebElement svg = getByXpath(Widget.getTopWidgetMainXpath() + Xpathes.SVG_TAG);
        double initWidth = Double.valueOf(svg.getAttribute("width"));
        home.increaseDemoChartWidth(700);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        assertTrue(Double.valueOf(svg.getAttribute("width")) > initWidth);
        home.increaseDemoChartWidth(-700);
        login.logoutWithApi();
    }

    @Test
    public void dashboard_proposes_the_new_size_using_gray_square_DEMO() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        home.checkGreyZoneDuringResize();
        login.logoutWithApi();
    }

    @Test
    public void shouldFailWithScreenshot() throws Exception {
        Login login = new Login();
        login.loginWithApi(Users.USER);
        Thread.sleep(4000);
        assertTrue(false);
    }

    @Test
    public void scrollTo_elementIsOnView_waitJsLoad_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        waitPageToLoad();
        assertNotVisible(Widget.getBottomWidgetMainXpath());
        assertVisible(Widget.getTopWidgetMainXpath());
        scrollTo(Widget.getBottomWidgetElement());
        waitPageToLoad();
        assertVisible(Widget.getBottomWidgetMainXpath());
        assertNotVisible(Widget.getTopWidgetMainXpath());
        scrollTo(Widget.getTopWidgetElement());
        waitPageToLoad();
        assertNotVisible(Widget.getBottomWidgetMainXpath());
        assertVisible(Widget.getTopWidgetMainXpath());
        login.logoutWithApi();
    }

    @Test
    public void move_bottom_widget_to_the_top_and_back_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        waitPageToLoad();
        assertEquals(Widget.getTopWidgetYPos(), 0); // Verify other widgets moves as well
        assertNotVisible(Widget.getBottomWidgetMainXpath());
        scrollTo(Widget.getBottomWidgetElement());
        waitPageToLoad();
        assertVisible(Widget.getBottomWidgetMainXpath());
        home.moveBottomWidgetToTheTop();
        home.clickLogo();
        home.openDemoDashboard();
        assertNotEquals(Widget.getTopWidgetYPos(), 0); // Verify other widgets moves as well
        assertVisible(Widget.getBottomWidgetMainXpath());
        home.moveBottomWidgetToTheBottom();
        home.clickLogo();
        home.openDemoDashboard();
        assertEquals(Widget.getTopWidgetYPos(), 0); // Verify other widgets moves as well
        assertNotVisible(Widget.getBottomWidgetMainXpath());
        login.logoutWithApi();
    }

    @Test
    public void dashboard_proposes_the_new_position_using_gray_square_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        waitPageToLoad();
        home.checkGreyZoneDuringMove();
        home.decreaseDemoChartHeight();
        login.logoutWithApi();
    }

    @Test
    public void verify_content_scrolling_independently_from_page_for_widget_OVERALL_STATISTICS_PANEL_Demo() {
        Login login = new Login();
        Home home = new Home();
        login.loginWithApi(Users.USER);
        home.openDemoDashboard();
        waitPageToLoad();
        scrollTo(Widget.getOverallStatisticsWidgetElement());
        waitPageToLoad();
        String initRightPartTopPosition = getCssValue(By.xpath(Widget.getOSRightPartScrollerXpath()), "top");
        String initLeftPartTopPosition = getCssValue(By.xpath(Widget.getOSLeftPartScrollerXpath()), "top");
        hoverElement1DragElement2(By.xpath(Widget.getOSLeftPartXpath()), By.xpath(Widget.getOSLeftPartScrollerXpath()), 0, 50);
        String currentRightPartTopPosition = getCssValue(By.xpath(Widget.getOSRightPartScrollerXpath()), "top");
        String currentLeftPartTopPosition = getCssValue(By.xpath(Widget.getOSLeftPartScrollerXpath()), "top");
        assertEquals(initRightPartTopPosition, currentRightPartTopPosition);
        assertNotEquals(initLeftPartTopPosition, currentLeftPartTopPosition);
        login.logoutWithApi();
    }
}
