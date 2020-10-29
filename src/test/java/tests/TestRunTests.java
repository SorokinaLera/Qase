package tests;

import io.qameta.allure.Description;
import models.TestRun;
import org.testng.annotations.Test;

public class TestRunTests extends BaseTest {

    @Description("Start new test run")
    @Test
    public void createNewTestRun() {
        TestRun testRun = TestRun.builder()
                .testRunTitle(faker.friends().character())
                .description(faker.friends().quote())
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
                .validateThatTestRunIsCreated(testRun.getTestRunTitle());
    }

    @Description("Checking the information about created test run")
    @Test
    public void runNewTestRun() {
        TestRun testRun = TestRun.builder()
                .testRunTitle(faker.name().title())
                .description(faker.chuckNorris().fact())
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
        testRunPage
                .clickOnStartTestRunCreatingButton()
                .addTestRunParameters(testRun)
                .checkDataInTheCreatedTestRun(testRun.getTestRunTitle(), testRun.getDescription());

    }

    @Description("Delete test run")
    @Test
    public void deleteTestPlan() {
        TestRun testRun = TestRun.builder()
                .testRunTitle(faker.harryPotter().character())
                .description(faker.harryPotter().quote())
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
        testRunPage
                .clickOnStartTestRunCreatingButton()
                .addTestRunParameters(testRun)
                .validateThatTestRunIsCreated(testRun.getTestRunTitle());
        testRunPage
                .deleteTestRun(testRun.getTestRunTitle())
                .refreshPage()
                .validateThatTestRunDoesNotExist(testRun.getTestRunTitle());
    }

}
