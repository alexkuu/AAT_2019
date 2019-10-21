package uiTests;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import service.ui.Driver;

public class BaseTest {
    final static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void init() throws Exception {
        Driver.Initialize();
        logger.info("Before class");
    }

    @AfterClass
    public void shutDown(){
        Driver.close();
        logger.info("Shut down");
    }


}
