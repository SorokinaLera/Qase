package tests;

import io.qameta.allure.Description;
import models.TestPlan;
import org.testng.annotations.Test;

public class TestPlanTests extends BaseTest {

    @Description("Create New Test plan")
    @Test
    public void createNewTestPlan() {
        TestPlan testPlan = TestPlan.builder()
                .testPlanTitle("New Test Plan for tests")
                .description("some description")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestPlanPage();
        testPlansPage
                .clickOnNewTestPlanCreatingButton()
                .addTestPlanParameters(testPlan)
                .validateThatTestPlanIsCreated(testPlan);
    }

    @Description("Check data in the created test plan")
    @Test
    public void runNewTestPlan() {
        TestPlan testPlan = TestPlan.builder()
                .testPlanTitle("Test Plan for runNewTestPlan()")
                .description("some more description")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestPlanPage();
        testPlansPage
                .clickOnNewTestPlanCreatingButton()
                .addTestPlanParameters(testPlan)
                .validateThatTestPlanIsCreated(testPlan)
                .checkDataInTheCreatedTestPlan("Test Plan for runNewTestPlan()", testPlan);
    }

    @Description("Delete Test plan")
    @Test
    public void deleteTestPlan() {
        TestPlan testPlan = TestPlan.builder()
                .testPlanTitle("Delete me Test Plan")
                .description("some description")
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestPlanPage();
        testPlansPage
                .clickOnNewTestPlanCreatingButton()
                .addTestPlanParameters(testPlan)
                .validateThatTestPlanIsCreated(testPlan)
                .deleteTestPlan(testPlan.getTestPlanTitle())
                .refreshPage()
                .validateThatTestPlanDoesNotExist(testPlan);
    }
}
