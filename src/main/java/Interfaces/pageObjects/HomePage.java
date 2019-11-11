package Interfaces.pageObjects;

public interface HomePage {

    void logout();
    void clickLogo();
    void openSettings();
    void clickAddNewDashBoard();
    void inputNewDashboardName(String name);
    void clickAddDashBoard();
    void deleteDashboard(String name);
    boolean isDashboardDisplayed(String name);
    boolean isDashBoardListDisplayed();
    void openDemoDashboard();
    void dragNDropDemoChartByOffset(int x, int y);
    boolean demoChartOnTheRightSide();
    boolean demoChartOnTheLeftSide();
    void increaseDemoChartHeight();
    void decreaseDemoChartHeight();
    int getDemoChartHeight();
    int getDemoChartWidth();
    void increaseDemoChartWidth(int x);

}
