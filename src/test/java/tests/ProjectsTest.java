package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import utils.Retry;

public class ProjectsTest extends BaseTest {

    @Description("Create New Project")
    @Test(retryAnalyzer = Retry.class)
    public void createNewProject(){
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened()
                .createNewProject("new1", "someCode", "someDescription");
        projectsPage
                .openPage()
                .validateThatNewProjectIsInProjectsList("new1");
    }

    @Test(retryAnalyzer = Retry.class)
    public void openProject(){
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened()
                .openProject("Demo")
                .isPageOpened();
    }

}
