package dataProvider;

import org.testng.annotations.DataProvider;
import service.Config;

public class SmokeDataProvider {

    @DataProvider
    public static Object[][] users() {
        return new Object[][]{
                {"default", "1q2w3e"},
                {Config.getAdminName(), Config.getAdminPassword()}
        };
    }

}
