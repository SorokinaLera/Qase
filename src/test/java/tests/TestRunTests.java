package tests;

import io.qameta.allure.Description;
import models.TestRun;
import org.testng.annotations.Test;

public class TestRunTests extends BaseTest {

    @Description("Start New Test Run")
    @Test
    public void createNewTestRun() {
        TestRun testRun = TestRun.builder()
                .testRunTitle(":(")
                .description("some description")
                .plan("New")
                .environment("")
                .milestone("")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestRunPage();
        testRunPage.clickOnStartTestRunCreatingButton()
                .addTestRunParameters(testRun)
                .validateThatTestRunIsCreated(testRun);
    }

    @Description("**")
    @Test
    public void runNewTestRun() {
        TestRun testRun = TestRun.builder()
                .testRunTitle("Some Title")
                .description("some description")
                .plan("New")
                .environment("")
                .milestone("")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestRunPage();
        testRunPage.clickOnStartTestRunCreatingButton()
                .addTestRunParameters(testRun)
                .checkDataInTheCreatedTestRun("Some Title", testRun);

    }

    @Description("Delete Test Run")
    @Test
    public void deleteTestRun() {
        TestRun testRun = TestRun.builder()
                .testRunTitle("Some Title for delete.Again1")
                .description("some description")
                .plan("New")
                .environment("")
                .milestone("")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestRunPage();
        testRunPage.clickOnStartTestRunCreatingButton()
                .addTestRunParameters(testRun)
                .validateThatTestRunIsCreated(testRun)
                .deleteTestRun(testRun.getTestRunTitle())
                .refreshPage()
                .validateThatTestRunDoesNotExist(testRun.getTestRunTitle());
    }

    @Test
    public void deleteAllTestRuns() {
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestRunPage();
        testRunPage
                .deleteAll();
    }

}
