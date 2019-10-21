package service.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import service.Config;

import java.util.concurrent.TimeUnit;

public class Driver {
    final static Logger logger = Logger.getLogger(Driver.class);


    private static String baseUrl = Config.getUrl();
    private static String browser = Config.getBrowser();
    private static WebDriver webDriver;

    public static void Initialize() throws Exception {
        webDriver = WebDriverFactory.getInstance(browser);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        logger.info("Driver initialization");
    }

    public static WebDriver getDriver(){
        return webDriver;
    }

    public static void goTo(String url){
        webDriver.get(baseUrl + url);
        logger.info("Open " + url);
    }

    public static void close(){
        webDriver.close();
    }

    public static String getTitle(){
        logger.info("Get title");
        return webDriver.getTitle();
    }

}
