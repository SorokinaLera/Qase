package pages;

import io.qameta.allure.Step;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectsPage extends BasePage {
    public static String URL = "projects";
    public static String projectName = "//*[contains(@class, 'defect-title') and contains(text(),'%s')]";
    public static final By PROJECTS_LIST = By.cssSelector(".project-row");
    public static final By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    public static final By PROJECT_NAME_INPUT = By.id("inputTitle");
    public static final By PROJECT_CODE_INPUT = By.id("inputCode");
    public static final By PROJECT_DESCRIPTION_INPUT = By.id("inputDescription");
    public static final By SUBMIT_BUTTON = By.cssSelector("*[type='submit']");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validate that the page is opened")
    public ProjectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PROJECTS_LIST));
        return this;
    }

    @Step("Open home page")
    public ProjectsPage openPage() {
        driver.get(URN + URL);
        isPageOpened();
        return this;
    }

    public ProjectsPage clickOnCreateNewProjectButton(){
        driver.findElement(CREATE_NEW_PROJECT_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SUBMIT_BUTTON));
        return this;
    }

    @Step("Create new project")
    public ProjectsPage addProjectData(Project project){
        driver.findElement(PROJECT_NAME_INPUT).sendKeys(project.getName());
        driver.findElement(PROJECT_CODE_INPUT).sendKeys(project.getCode());
        driver.findElement(PROJECT_DESCRIPTION_INPUT).sendKeys(project.getDescription());
        return this;
    }

    public ProjectPage submitProjectCreating(){
        driver.findElement(SUBMIT_BUTTON).click();
        return new ProjectPage(driver);
    }

    @Step("Validate that \"{name}\" is in Projects List")
    public boolean validateThatNewProjectIsInProjectsList(String name){
        return driver.findElement(By.xpath(String.format(projectName, name))).isDisplayed();
    }

    @Step("Open project \"{name}\"")
    public ProjectPage openProject(String name){
        driver.findElement(By.xpath(String.format(projectName, name))).click();
        return new ProjectPage(driver);
    }
}
