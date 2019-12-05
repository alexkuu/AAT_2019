package cucumberTests.stepdefs;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import service.ui.Driver;
import service.ui.DriverManager;
import uiTests.BaseTest;

public class ServiceHooks {
    final static Logger logger = Logger.getLogger(BaseTest.class);

    @Before
    public void initializeTest() {
        DriverManager.set(new Driver());
        WebDriverRunner.setWebDriver(DriverManager.getDriver());
        logger.info("Before class");
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                logger.info("Screenshot created: " + DriverManager.makeScreenshot(scenario.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DriverManager.getDriver().close();
        logger.info("Shut down");
    }
}