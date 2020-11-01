package tests;

import io.qameta.allure.Description;
import models.Project;
import org.testng.annotations.Test;
import utils.Retry;

public class ProjectsTest extends BaseTest {

    @Description("Create New Project")
    @Test(retryAnalyzer = Retry.class)
    public void createNewProject(){
        Project newProject = Project.builder()
                .name(faker.elderScrolls().firstName())
                .code(faker.elderScrolls().city())
                .description(faker.elderScrolls().race())
                .build();
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened()
                .createNewProject(newProject);
        projectsPage
                .openPage()
                .validateThatNewProjectIsInProjectsList(newProject.getName());
    }

    @Description("Open project")
    @Test(retryAnalyzer = Retry.class)
    public void openProject(){
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened()
                .openProject("Qase")
                .isPageOpened();
    }
}
