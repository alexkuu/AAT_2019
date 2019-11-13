package service.assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import service.ui.MiscActions;

public class Assertion {

    public static void assertVisible(WebElement element) {
        Assert.assertTrue(MiscActions.elementIsOnView(element), "Expected visible, but it not for [" + element + "]");
    }

    public static void assertVisible(By by) {
        Assert.assertTrue(MiscActions.elementIsOnView(by), "Expected visible, but it not for [" + by + "]");
    }

    public static void assertVisible(String xpath) {
        Assert.assertTrue(MiscActions.elementIsOnView(By.xpath(xpath)), "Expected visible, but it not for [" + By.xpath(xpath) + "]");
    }

    public static void assertNotVisible(WebElement element) {
        Assert.assertFalse(MiscActions.elementIsOnView(element), "Expected not visible, but it is for [" + element + "]");
    }

    public static void assertNotVisible(By by) {
        Assert.assertFalse(MiscActions.elementIsOnView(by), "Expected not visible, but it is for [" + by + "]");
    }

    public static void assertNotVisible(String xpath) {
        Assert.assertFalse(MiscActions.elementIsOnView(By.xpath(xpath)), "Expected not visible, but it is for [" + By.xpath(xpath) + "]");
    }

    public static void assertEquals(Object a, Object b) {
        Assert.assertEquals(a, b, "Equals assertion failed");
    }

    public static void assertNotEquals(Object a, Object b) {
        Assert.assertNotEquals(a, b, "Not-equals assertion failed\nBoth values are: " + a);
    }

    public static void assertTrue(boolean val) {
        Assert.assertTrue(val, "AssertTrue failed.");
    }

    public static void assertFalse(boolean val) {
        Assert.assertFalse(val, "AssertFalse failed.");
    }


}
