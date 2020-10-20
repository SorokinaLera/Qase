package tests;

import org.testng.annotations.Test;
import utils.Retry;

public class ProjectsTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void createNewProject(){
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened()
                .createNewProject("new1", "someCode", "someDescription");
        projectsPage
                .validateThatNewProjectIsInProjectsList("new1");
        projectsPage
                .deleteProject("new1");
    }
}
