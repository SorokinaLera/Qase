package tests;

import io.qameta.allure.Description;
import models.TestCase;
import models.TestSuite;
import org.testng.annotations.Test;
import utils.Retry;

public class ProjectTest extends BaseTest {

    @Description("Create new test suite")
    @Test(retryAnalyzer = Retry.class)
    public void createNewTestSuite() {
        TestSuite testSuite = TestSuite.builder()
                .suiteName(faker.lebowski().actor())
                .parent("")
                .description(faker.lebowski().quote())
                .preconditions(faker.lebowski().quote())
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .clickOnNewSuiteCreatingButton()
                .addTestSuiteParameters(testSuite)
                .clickOnSaveButton()
                .refreshPage()
                .validateThatNewSuiteIsCreated(testSuite.getSuiteName());
    }

    @Description("Delete test suite")
    @Test(retryAnalyzer = Retry.class)
    public void deleteTestSuite() {
        TestSuite testSuiteForDelete = TestSuite.builder()
                .suiteName(faker.rickAndMorty().character())
                .parent("")
                .description(faker.rickAndMorty().quote())
                .preconditions(faker.rickAndMorty().quote())
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .clickOnNewSuiteCreatingButton()
                .addTestSuiteParameters(testSuiteForDelete)
                .clickOnSaveButton()
                .deleteSuite(testSuiteForDelete.getSuiteName())
                .refreshPage()
                .validateThatSuiteDoesNotExist(testSuiteForDelete.getSuiteName());
    }

    @Description("Create new test case")
    @Test(retryAnalyzer = Retry.class)
    public void createNewTestCase() {
        TestCase testCase = TestCase.builder()
                .title(faker.gameOfThrones().character())
                .status("Actual")
                .description(faker.gameOfThrones().quote())
                .suite("Teams")
                .severity("Minor")
                .priority("Low")
                .type("Integration")
                .milestone("Release 1.1")
                .behavior("Positive")
                .automationStatus("To be automated")
                .preConditions(faker.gameOfThrones().city())
                .postConditions(faker.gameOfThrones().dragon())
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

    @Description("Delete test case")
    @Test(retryAnalyzer = Retry.class)
    public void deleteTestCase() {
        TestCase caseForDelete = TestCase.builder()
                .title(faker.beer().name())
                .status("Actual")
                .description(faker.beer().style())
                .suite("Teams")
                .severity("Minor")
                .priority("Low")
                .type("Integration")
                .milestone("Release 1.1")
                .behavior("Positive")
                .automationStatus("To be automated")
                .preConditions(faker.beer().malt())
                .postConditions(faker.beer().hop())
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
