package uiTests;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import service.thirdPartyIntergration.Slack;
import service.thirdPartyIntergration.TRail;
import service.ui.Driver;
import service.ui.DriverManager;

import java.lang.reflect.Method;

public class AnotherBaseTest {
    final static Logger logger = Logger.getLogger(AnotherBaseTest.class);

//    private TRail tRail;

    @BeforeClass
    public void initClass() {
//        tRail = new TRail().initRun();
    }

    @BeforeMethod
    public void init(Method method) {
//        tRail.addTestToRun(method.getName());
        DriverManager.set(new Driver());
        WebDriverRunner.setWebDriver(DriverManager.getDriver());
        logger.info("Before class");
//        Slack.reportTestStart(method.getName());
    }

    @AfterMethod
    public void shutDown(ITestResult result) {
//        tRail.markTestFinished(result);
//        Slack.reportTestFinish(result);
        logger.info("Shut down");
        DriverManager.getDriver().close();
    }

    @AfterClass
    public void shutdownClass() {
//        tRail.finishRun();
    }
}
