package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class SuiteCaseTest extends BaseTest {

    @Description("Create new Suite Case")
    @Test
    public void createNewSuiteCase(){
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
        projectsPage
                .openProject("Qase")
                .validateThatProjectIsOpened("Qase");
        createSuitePage.createNewSuite("New Suite777");
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
                .validateThatProjectIsOpened("Qase");
        createSuitePage.deleteSuite("New Suite777");
    }
}
