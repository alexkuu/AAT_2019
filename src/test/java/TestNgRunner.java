import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestNgRunner {

    public static void main(String[] args){
        TestNG testng = new TestNG();

        List<String> suites = new ArrayList<String>();
        suites.add("TestNgUiSuite.xml");
        suites.add("TestNgApiSuite.xml");

        testng.setTestSuites(suites);
        testng.run();

    }
}
