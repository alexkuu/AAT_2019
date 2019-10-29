package uiTests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import service.ui.Driver;
import service.ui.DriverManager;

public class BaseTest {
    final static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeEach
    public void init() {
        DriverManager.set(new Driver());
        logger.info("Before class");
    }

    @AfterEach
    public void shutDown(){
        DriverManager.get().close();
        logger.info("Shut down");
    }


}
