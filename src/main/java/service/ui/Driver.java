package service.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import service.Config;

import java.util.concurrent.TimeUnit;

public class Driver {
    final static Logger logger = Logger.getLogger(Driver.class);

    private String baseUrl = Config.getUrl();
    private WebDriver webDriver;

    public Driver() {
        logger.info("Driver initialization");
        try {
            this.webDriver = Initialize();
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WebDriver Initialize() throws Exception {
        return new WebDriverFactory().getInstance();
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void goTo(String url) {
        webDriver.get(baseUrl + url);
        logger.info("Open " + baseUrl + url);
    }

    public void close() {
        webDriver.close();
    }

    public String getTitle() {
        logger.info("Get title");
        return webDriver.getTitle();
    }

}
