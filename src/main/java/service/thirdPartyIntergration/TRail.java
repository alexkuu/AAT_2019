package service.thirdPartyIntergration;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;
import org.testng.ITestResult;
import service.Config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TRail {

    private TestRail testRail;
    private Suite suite;
    private Project project;
    private Section section;
    private List<CaseField> customCaseFields;
    private List<Case> testsInRun;
    private Run run;
    private List<ResultField> customResultFields;

    public TRail() {
        init();
    }

    private void init() {
        testRail = TestRail.builder(Config.getTestRailUrl(), Config.getTestRailUsername(), Config.getTestRailPassword()).applicationName("playground").build();
    }

    public TRail initRun() {
        testsInRun = new ArrayList<>();
        project = testRail.projects().list().execute().get(0);
        suite = testRail.suites().list(project.getId()).execute().get(0);
        section = addGetSection("Auto tests");
        customCaseFields = testRail.caseFields().list().execute();
        customResultFields = testRail.resultFields().list().execute();
        run = testRail.runs().add(project.getId(), new Run().setSuiteId(suite.getId()).setName("Auto tests" + new Date())).execute();
        return this;
    }

    private Section addGetSection(String name) {
        Section section = null;
        List<Section> sections = testRail.sections().list(project.getId()).execute();
        for (Section s : sections) {
            if (s.getName().equals(name)) {
                section = s;
                break;
            }
        }
        if (section == null) {
            section = new Section().setSuiteId(suite.getId()).setName(name);
            testRail.sections().add(project.getId(), section).execute();
        }
        return section;
    }

    public void finishRun() {
        testRail.runs().close(run.getId()).execute();
        testRail.projects().update(project.setCompleted(true)).execute();
    }

    public void markTestFinished(ITestResult result) {
        Case tCase = null;
        for (Case c : testsInRun) {
            if (c.getTitle().equals(result.getName())) {
                tCase = c;
                break;
            }
        }

        assert tCase != null;
        testRail.results().addForCase(run.getId(), tCase.getId(), new Result().setStatusId((result.isSuccess() ? 1 : 5)), customResultFields).execute();
    }

    public void addTestToRun(String testName) {
        List<Case> existingTestCases = testRail.cases().list(project.getId(), customCaseFields).execute();
        Case tCase = null;

        for (Case c : existingTestCases) {
            if (c.getTitle().equals(testName)) {
                tCase = c;
                break;
            }
        }

        if (tCase == null) {
            tCase = new Case().setTitle(testName);
            tCase = testRail.cases().add(section.getId(), tCase, customCaseFields).execute();
        }
        testsInRun.add(tCase);
    }
}
