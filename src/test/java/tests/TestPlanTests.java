package tests;

import io.qameta.allure.Description;
import models.TestPlans;
import org.testng.annotations.Test;


public class TestPlanTests extends BaseTest {

    @Description("Create New Test plan")
    @Test
    public void createNewTestPlan() {
        TestPlans testPlan = TestPlans.builder()
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
                .validateThatTestPlanIsCreated(testPlan.toString());
    }

    @Description("Delete Test plan")
    @Test
    public void deleteSuiteCase() {
        TestPlans testPlan = TestPlans.builder()
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
                .validateThatTestPlanIsCreated(testPlan.getTestPlanTitle())
                .deleteTestPlan(testPlan.getTestPlanTitle())
                .refreshPage()
                .validateThatTestPlanDoesNotExist(testPlan.getTestPlanTitle());
    }
}
