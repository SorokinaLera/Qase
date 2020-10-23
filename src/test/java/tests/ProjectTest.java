package tests;

import io.qameta.allure.Description;
import models.TestSuite;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {

    @Description("Create new Suite Case")
    @Test
    public void createNewSuiteCase(){
        TestSuite testSuite = TestSuite.builder()
                .suiteName("SUITENAME2")
                .parentSuite("No parent suite")
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
                .clickOnNewSuiteCreatingButton(testSuite)
                .addTestSiteParameters(testSuite)
                .clickOnSaveButton()
                .refreshPage()
                .validateThatNewSuiteIsCreated(testSuite.toString());
    }

    @Description("Delete new Suite Case")
    @Test
    public void deleteSuiteCase(){
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .validateThatProjectIsOpened("Qase")
                .deleteSuite("SUITENAME2")
                .refreshPage()
                .validateThatSuiteDoesNotExist("SUITENAME2");
    }
}
