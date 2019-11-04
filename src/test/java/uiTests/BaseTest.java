package uiTests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import service.ui.Driver;
import service.ui.DriverManager;

public class BaseTest {
    final static Logger logger = Logger.getLogger(BaseTest.class);

    @Before
    public void init() {
        DriverManager.set(new Driver());
        logger.info("Before class");

    }

    @After
    public void shutDown(){
        assert true;
        DriverManager.get().close();
        logger.info("Shut down");
    }


}
