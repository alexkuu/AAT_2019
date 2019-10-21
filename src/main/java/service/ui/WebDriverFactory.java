package service.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private WebDriverFactory() {
    }

    public static WebDriver getInstance(String browserName) throws Exception {
        WebDriver webDriver = null;

        if (CHROME.equals(browserName.toLowerCase())) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }
        if (FIREFOX.equals(browserName.toLowerCase())) {
            webDriver = new FirefoxDriver();
        }
        if (webDriver == null) {
            throw new Exception("Not supported browser: " + browserName);
        }
        return webDriver;
    }
}
