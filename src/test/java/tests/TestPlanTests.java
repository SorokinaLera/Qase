package tests;

import io.qameta.allure.Description;
import models.TestPlan;
import org.testng.annotations.Test;
import utils.Retry;

public class TestPlanTests extends BaseTest {

    @Description("Create new test plan")
    @Test(retryAnalyzer = Retry.class)
    public void createNewTestPlan() {
        TestPlan testPlan = TestPlan.builder()
                .testPlanTitle(faker.dune().title())
                .description(faker.dune().quote())
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestPlanPage();
        testPlanPage
                .clickOnNewTestPlanCreatingButton()
                .addTestPlanParameters(testPlan)
                .validateThatTestPlanIsCreated(testPlan.getTestPlanTitle());
    }

    @Description("Check data in the created test plan")
    @Test(retryAnalyzer = Retry.class)
    public void runNewTestPlan() {
        TestPlan testPlan = TestPlan.builder()
                .testPlanTitle(faker.dune().title())
                .description(faker.dune().quote())
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestPlanPage();
        testPlanPage
                .clickOnNewTestPlanCreatingButton()
                .addTestPlanParameters(testPlan)
                .validateThatTestPlanIsCreated(testPlan.getTestPlanTitle());
        testPlanPage
                .checkDataInTheCreatedTestPlan(testPlan.getTestPlanTitle(), testPlan.getDescription());
    }

    @Description("Delete test plan")
    @Test(retryAnalyzer = Retry.class)
    public void deleteTestPlan() {
        TestPlan testPlan = TestPlan.builder()
                .testPlanTitle(faker.witcher().character())
                .description(faker.witcher().quote())
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .goToTestPlanPage();
        testPlanPage
                .clickOnNewTestPlanCreatingButton()
                .addTestPlanParameters(testPlan)
                .validateThatTestPlanIsCreated(testPlan.getTestPlanTitle());
        testPlanPage
                .deleteTestPlan(testPlan.getTestPlanTitle())
                .refreshPage()
                .validateThatTestPlanDoesNotExist(testPlan.getTestPlanTitle());
    }
}
