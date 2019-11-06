package dataProvider;

import org.testng.annotations.DataProvider;
import service.Config;

public class SmokeDataProvider {

    @DataProvider
    public static Object[][] users() {
        return new Object[][]{
                {"user"},
                {"admin"}
        };
    }

}
