package uiTests;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import service.ui.Driver;
import service.ui.DriverManager;

public class BaseTest {
    final static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeMethod
    public void init() {
        DriverManager.set(new Driver());
        logger.info("Before class");
    }

    @AfterMethod
    public void shutDown() {
        DriverManager.get().close();
        logger.info("Shut down");
    }


}
