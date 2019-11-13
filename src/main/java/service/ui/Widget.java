package service.ui;

import constants.AppContstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Widget {

    // Xpath patterns
    private static String widgetMain = "//div[@data-id='<id>']";
    private static String widgetResizer = widgetMain + "//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']";
    private static String widgetMover = widgetMain + "//div[@class='gadget-header ui-draggable-handle']";
    private static String widgetLeftPart = widgetMain + "//div[@class='left-widget-wrapper widget-wrapper']";
    private static String widgetLeftPartScroller = widgetLeftPart + "//div[@class='baron__bar baron__bar-vertical']";
    private static String widgetRightPart = widgetMain + "//div[@class='right-widget-wrapper widget-wrapper']";
    private static String widgetRightPartScroller = widgetRightPart + "//div[@class='baron__bar baron__bar-vertical']";

    public static WebElement getTopWidgetElement() {
        return DriverManager.getDriver().findElement(By.xpath(getTopWidgetMainXpath()));
    }

    public static WebElement getBottomWidgetElement() {
        return DriverManager.getDriver().findElement(By.xpath(getBottomWidgetMainXpath()));
    }

    public static WebElement getOverallStatisticsWidgetElement() {
        return DriverManager.getDriver().findElement(By.xpath(getOverallStatisticsWidgetMainXpath()));
    }

    public static WebElement getTopWidgetSizer() {
        return DriverManager.getDriver().findElement(By.xpath(getTopWidgetResizerXpath()));
    }

    public static WebElement getTopWidgetMover() {
        return DriverManager.getDriver().findElement(By.xpath(getTopWidgetMoverXpath()));
    }

    public static WebElement getBottomWidgetMover() {
        return DriverManager.getDriver().findElement(By.xpath(getBottomWidgetMoverXpath()));
    }

    public static int getTopWidgetWidth() {
        return Integer.valueOf(getTopWidgetElement().getAttribute("data-gs-width"));
    }

    public static int getTopWidgetHeight() {
        return Integer.valueOf(getTopWidgetElement().getAttribute("data-gs-height"));
    }

    public static int getBottomWidgetHeight() {
        return Integer.valueOf(getBottomWidgetElement().getAttribute("data-gs-height"));
    }

    public static int getTopWidgetXPos() {
        return Integer.valueOf(getTopWidgetElement().getAttribute("data-gs-x"));
    }

    public static int getTopWidgetYPos() {
        return Integer.valueOf(getTopWidgetElement().getAttribute("data-gs-y"));
    }

    public static int getBottomWidgetXPos() {
        return Integer.valueOf(getBottomWidgetElement().getAttribute("data-gs-x"));
    }

    public static int getBottomWidgetYPos() {
        return Integer.valueOf(getBottomWidgetElement().getAttribute("data-gs-y"));
    }

    // Get xpaths
    public static String getTopWidgetMainXpath(){
        return widgetMain.replace("<id>", AppContstants.WIDGET_TOP_ID);
    }

    public static String getTopWidgetResizerXpath(){
        return widgetResizer.replace("<id>", AppContstants.WIDGET_TOP_ID);
    }

    public static String getTopWidgetMoverXpath(){
        return widgetMover.replace("<id>", AppContstants.WIDGET_TOP_ID);
    }

    public static String getBottomWidgetMainXpath(){
        return widgetMain.replace("<id>", AppContstants.WIDGET_BOTTOM_ID);
    }

    public static String getOverallStatisticsWidgetMainXpath(){
        return widgetMain.replace("<id>", AppContstants.WIDGET_OVERALLSTATISTICS_ID);
    }

    public static String getBottomWidgetResizerXpath(){
        return widgetResizer.replace("<id>", AppContstants.WIDGET_BOTTOM_ID);
    }

    public static String getBottomWidgetMoverXpath(){
        return widgetMover.replace("<id>", AppContstants.WIDGET_BOTTOM_ID);
    }

    public static String getOSLeftPartXpath(){
        return widgetLeftPart.replace("<id>", AppContstants.WIDGET_OVERALLSTATISTICS_ID);
    }

    public static String getOSRightPartXpath(){
        return widgetRightPart.replace("<id>", AppContstants.WIDGET_OVERALLSTATISTICS_ID);
    }

    public static String getOSLeftPartScrollerXpath(){
        return widgetLeftPartScroller.replace("<id>", AppContstants.WIDGET_OVERALLSTATISTICS_ID);
    }

    public static String getOSRightPartScrollerXpath(){
        return widgetRightPartScroller.replace("<id>", AppContstants.WIDGET_OVERALLSTATISTICS_ID);
    }

}
