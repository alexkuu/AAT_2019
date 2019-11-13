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

import java.net.URI;
import java.net.URL;

public class WebDriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    protected WebDriverFactory() {
    }

    public WebDriver getInstance() throws Exception {
        WebDriver webDriver = null;

        if (Config.isRemoteRun()) {
            String hubUrl;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (Config.getRemoteApp().equals("selenoid")) {

                // Selenoid

                hubUrl = "http://" + Config.getSelenoidHost() + ":" + Config.getGSelenoidPort() + "/wd/hub";
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                capabilities.setBrowserName(Config.getBrowser());
                if (CHROME.equals(Config.getBrowser().toLowerCase())) {
                    capabilities.setVersion("78.0");
                } else if (FIREFOX.equals(Config.getBrowser().toLowerCase())) {
                    capabilities.setVersion("70.0");
                } else {
                    throw new Exception("Not supported browser: " + Config.getBrowser());
                }
                webDriver = new RemoteWebDriver(URI.create(hubUrl).toURL(), capabilities);

            } else if (Config.getRemoteApp().equals("grid")) {

                //Selenium grid

                hubUrl = "http://" + Config.getGridHost() + ":" + Config.getGridPort() + "/wd/hub";
                capabilities.setBrowserName(Config.getBrowser());

                if (CHROME.equals(Config.getBrowser().toLowerCase())) {
                    capabilities.setPlatform(Platform.WINDOWS);
                    ChromeOptions options = new ChromeOptions();
                    options.merge(capabilities);
                    webDriver = new RemoteWebDriver(new URL(hubUrl), options);
                } else if (FIREFOX.equals(Config.getBrowser().toLowerCase())) {
                    capabilities.setPlatform(Platform.ANY);
                    capabilities.setCapability("marionette", true);
                    FirefoxOptions opt = new FirefoxOptions();
                    opt.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                    opt.merge(capabilities);
                    webDriver = new RemoteWebDriver(new URL(hubUrl), opt);
                }
            }
        } else {
            if (CHROME.equals(Config.getBrowser().toLowerCase())) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
            } else if (FIREFOX.equals(Config.getBrowser().toLowerCase())) {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            }
        }
        if (webDriver == null) {
            throw new Exception("Not supported browser: " + Config.getBrowser());
        }
        return webDriver;
    }
}
