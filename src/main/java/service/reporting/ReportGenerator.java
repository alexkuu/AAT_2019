package service.reporting;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportGenerator {

    private int total;
    private int failures;
    private int ignored;
    private long runTime;
    private String testClassName;
    private List<Failure> failureList;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private String reportName;

    public ReportGenerator(Class klass, Result result) {
        total = result.getRunCount();
        failures = result.getFailureCount();
        failures = result.getFailureCount();
        ignored = result.getIgnoreCount();
        runTime = result.getRunTime();
        failureList = result.getFailures();
        testClassName = klass.getName();
        reportName = "target/" + testClassName + "_" + formatter.format(new Date(System.currentTimeMillis())) + ".html";
    }

    public void makeReport() {
        if (new File(reportName).exists()) {
            new File(reportName).delete();
        }
        String content = generateHtml();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(reportName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : content.split("\n")) {
            writer.println(line);
        }
        writer.close();
    }

    private String generateHtml() {
        String content = "<html><head><title>" + testClassName + " tests report</title>" + includeStyles() + "</head><body>";
        content += "<div class=\"top-banner-root\">\n" +
                "      <span class=\"top-banner-title-font\">Test results</span>\n" +
                "      <br>\n" +
                "      <span class=\"top-banner-font-1\">1 suite, " + total + " total tests, " + failures + " failed tests</span>\n" +
                "    </div><br/><br/><br/>";
        if (failures > 0) {
            content += "<div class=\"suite-Gradle_suite-class-failed\">\n" +
                    "            <div class=\"main-panel-header rounded-window-top\">\n" +
                    "              \n" +
                    "              <span class=\"class-name\">" + testClassName + "</span>\n" +
                    "            </div> <!-- main-panel-header rounded-window-top -->\n" +
                    "            <div class=\"main-panel-content rounded-window-bottom\">\n";

            for (Failure failure : failureList) {
                content += "<div class=\"method\"><div class=\"method-content\">" +
                        "<a name=\"" + failure.getDescription() + "\"></a>" +
                        "<span class=\"method-name\"><b>" + failure.getDescription() + "</b></span><br/>" +
                        "<div class=\"stack-trace\">" + failure.getMessage() + "\n\n" + failure.getTrace() +
                        "</div></div></div><br/><br/>";
            }

            content += "            </div> <!-- main-panel-content rounded-window-bottom -->\n" +
                    "          </div>";
        } else {
            content += "<h1>ALL TESTS PASSED</h1>";
        }

        content += "</body></html>";
        return content;
    }

    private String includeStyles() {
        String styles = "<style>\n" +
                ".top-banner-root {\n" +
                "    position: absolute;\n" +
                "    top: 0;\n" +
                "    height: 45px;\n" +
                "    left: 0;\n" +
                "    right: 0;\n" +
                "    padding: 5px;\n" +
                "    margin: 0px 0px 5px 0px;\n" +
                "    background-color: #0066ff;\n" +
                "    font-family: Times;\n" +
                "    color: #fff;\n" +
                "    text-align: center;\n" +
                "}" +
                ".main-panel-header {\n" +
                "    padding: 5px;\n" +
                "    background-color: #9FB4D9;\n" +
                "    //afeeee: ;\n" +
                "    font-family: monospace;\n" +
                "    font-size: 18px;\n" +
                "}" +
                ".rounded-window-top {\n" +
                "    border-top-right-radius: 10px 10px;\n" +
                "    border-top-left-radius: 10px 10px;\n" +
                "    border-style: solid;\n" +
                "    border-width: 1px;\n" +
                "    overflow: auto;\n" +
                "}" +
                ".main-panel-content {\n" +
                "    padding: 5px;\n" +
                "    margin-bottom: 10px;\n" +
                "    background-color: #DEE8FC;\n" +
                "    //d0ffff: ;\n" +
                "}" +
                ".method-content {\n" +
                "    border-style: solid;\n" +
                "    border-width: 0px 0px 1px 0px;\n" +
                "    margin-bottom: 10;\n" +
                "    padding-bottom: 5px;\n" +
                "    width: 80%;\n" +
                "}" +
                ".method-name {\n" +
                "    font-size: 18px;\n" +
                "    font-family: monospace;\n" +
                "}" +
                ".stack-trace {\n" +
                "    white-space: pre;\n" +
                "    font-family: monospace;\n" +
                "    font-size: 12px;\n" +
                "    font-weight: bold;\n" +
                "    margin-top: 0px;\n" +
                "    margin-left: 20px;\n" +
                "}"+
                "</style>";
        return styles;
    }
}
