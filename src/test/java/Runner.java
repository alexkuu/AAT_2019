import apiTests.SmokeApiTest;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import service.listeners.JUnitExecutionListener;
import service.reporting.ReportGenerator;
import uiTests.SmokeTests;

public class Runner {

    private static JUnitCore jUnitCore = new JUnitCore();
    private static JUnitExecutionListener listener = new JUnitExecutionListener();

    public static void main(String[] args) {
        jUnitCore.addListener(listener);
        run(ParallelComputer.methods(), SmokeApiTest.class);
        run(ParallelComputer.methods(), SmokeTests.class);
    }

    private static void run(Computer computer, Class klass) {
        new ReportGenerator(klass, jUnitCore.run(computer, klass)).makeReport();
    }
}
