package tests;

import models.TestCase;
import org.testng.annotations.Test;
import utils.Retry;

public class ProjectTest extends BaseTest {

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
                .clickOnSaveButton()
                .validateThatNewCaseIsCreated(testCase.getTitle());
    }
}
