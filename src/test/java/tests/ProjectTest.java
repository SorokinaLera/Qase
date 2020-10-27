package tests;

import io.qameta.allure.Description;
import models.TestSuite;
import org.testng.annotations.Test;
import models.TestCase;
import utils.Retry;

public class ProjectTest extends BaseTest {

    @Description("Create new Suite Case")
    @Test
    public void createNewSuiteCase(){
        TestSuite testSuite = TestSuite.builder()
                .suiteName("SUITENAME2")
                .parent("No parent suite")
                .description("description")
                .preconditions("preconditions")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .validateThatProjectIsOpened("Qase")
                .clickOnNewSuiteCreatingButton()
                .addTestSuiteParameters(testSuite)
                .clickOnSaveButton()
                .refreshPage()
                .validateThatNewSuiteIsCreated(testSuite.toString());
    }

    @Description("Delete new Suite Case")
    @Test
    public void deleteSuiteCase() {
        TestSuite testSuiteForDelete = TestSuite.builder()
                .suiteName("Delete me")
                .parent("No parent suite")
                .description("description")
                .preconditions("preconditions")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .validateThatProjectIsOpened("Qase")
                .deleteSuite(testSuiteForDelete.getSuiteName())
                .refreshPage()
                .validateThatSuiteDoesNotExist(testSuiteForDelete.getSuiteName());
    }

    @Test(retryAnalyzer = Retry.class)
    public void createNewTestCase(){
    TestCase testCase = TestCase.builder()
            .title("testCase for tests")
            .status("Actual")
            .description("some description")
            .suite("Teams")
            .severity("Minor")
            .priority("Low")
            .type("Integration")
            .milestone("Release 1.1")
            .behavior("Positive")
            .automationStatus("To be automated")
            .preConditions("Some pre-conditions")
            .postConditions("Some post-conditions")
            .build();

        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened()
                .openProject("Demo")
                .isPageOpened();
        projectPage
                .clickOnNewCaseCreatingButton()
                .addTestCaseParameters(testCase)
                .clickOnSaveTestCaseButton()
                .validateThatNewCaseIsCreated(testCase.getTitle());
    }

    @Test(retryAnalyzer = Retry.class)
    public void deleteTestCase(){
        TestCase caseForDelete = TestCase.builder()
                .title("Delete it")
                .status("Actual")
                .description("some description")
                .suite("Teams")
                .severity("Minor")
                .priority("Low")
                .type("Integration")
                .milestone("Release 1.1")
                .behavior("Positive")
                .automationStatus("To be automated")
                .preConditions("Some pre-conditions")
                .postConditions("Some post-conditions")
                .build();

        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened()
                .openProject("Demo")
                .isPageOpened();
        projectPage
                .clickOnNewCaseCreatingButton()
                .addTestCaseParameters(caseForDelete)
                .clickOnSaveTestCaseButton()
                .deleteTestCase(caseForDelete.getTitle())
                .refreshPage()
                .validateThatCaseDoesNotExist(caseForDelete.getTitle());
    }
}
