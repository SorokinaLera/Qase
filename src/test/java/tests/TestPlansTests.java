package tests;

import io.qameta.allure.Description;
import models.TestPlans;
import org.testng.annotations.Test;


public class TestPlansTests extends BaseTest{

    @Description("Create New Test plan")
    @Test
    public void createNewProject(){
        TestPlans testPlans = TestPlans.builder()
                .testPlanTitle("Test Plan for tests")
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
                .createTestPlan()
                .addTestPlanParameters(testPlans)
                .validateThatTestPlanIsCreated();
    }
}
