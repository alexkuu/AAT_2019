package service.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import service.Config;

import java.net.URL;

public class WebDriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    protected WebDriverFactory() {
    }

    public WebDriver getInstance(String browserName) throws Exception {
        WebDriver webDriver = null;

        if (Config.isRemoteRun()) {
            String hubUrl = "http://" + Config.getRemoteHost() + ":" + Config.getRemotePort() + "/wd/hub";
            if (CHROME.equals(browserName.toLowerCase())) {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setBrowserName(Config.getBrowser());
                cap.setPlatform(Platform.WINDOWS);
                ChromeOptions options = new ChromeOptions();
                options.merge(cap);
                webDriver = new RemoteWebDriver(new URL(hubUrl), options);
            } else if (FIREFOX.equals(browserName.toLowerCase())) {
                DesiredCapabilities dc = new DesiredCapabilities();
                dc.setBrowserName(Config.getBrowser());
                dc.setPlatform(Platform.ANY);
                dc.setCapability("marionette", true);
                FirefoxOptions opt = new FirefoxOptions();
                opt.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                opt.merge(dc);
                webDriver = new RemoteWebDriver(new URL(hubUrl), opt);
            }
        } else {
            if (CHROME.equals(browserName.toLowerCase())) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
            } else if (FIREFOX.equals(browserName.toLowerCase())) {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();

            }
        }
        if (webDriver == null) {
            throw new Exception("Not supported browser: " + browserName);
        }
        return webDriver;
    }
}
