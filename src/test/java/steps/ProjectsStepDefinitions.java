package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import models.Project;

public class ProjectsStepDefinitions extends BaseSteps {
    @Before
    public void setUp(){
        openBrowser();
    }

    @After()
    public  void tearDown(){
        closeBrowser();
    }

    @Given("Click on {string} button")
    public void clickOnButton(String button) {
            projectsPage
                    .submitProjectCreating();
    }

    @Then("The new project must exist")
    public boolean theNewProjectMustExist() {
        return projectsPage
                .openPage()
                .validateThatNewProjectIsInProjectsList(createANewProjectUsingRandomData().getName());
    }

    @Given("Create a new project using random data")
    public Project createANewProjectUsingRandomData() {
        Project newProject = Project.builder()
                .name(faker.elderScrolls().firstName())
                .code(faker.elderScrolls().city())
                .description(faker.elderScrolls().race())
                .build();
        projectsPage
                .clickOnCreateNewProjectButton()
                .addProjectData(newProject);
        return newProject;
    }
}