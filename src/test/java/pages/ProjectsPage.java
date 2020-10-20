package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectsPage extends BasePage {
    public static String URL = "https://app.qase.io/projects";
    public static String newProjectName = "//*[contains(text(),'%s')]";
    public static String projectToggle = "//*[contains(text(),'%s')]//ancestor::tr[@class='project-row']//a[@data-toggle='dropdown']";
    public static final By DELETE_BUTTON = By.xpath("//*[contains(text(),'Delete')]");
    public static final By DELETE_CONFIRMATION_BUTTON = By.cssSelector(".btn-cancel");
    public static final By PROJECTS_LIST = By.cssSelector(".project-row");
    public static final By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    public static final By PROJECT_NAME_INPUT = By.id("inputTitle");
    public static final By PROJECT_CODE_INPUT = By.id("inputCode");
    public static final By PROJECT_DESCRIPTION_INPUT = By.id("inputDescription");
    public static final By SUBMIT_BUTTON = By.cssSelector("*[type='submit']");
    public static final By TEST_REPOSITORY_PAGE_TITLE = By.xpath("//*[contains(text(),'Test repository')]");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public ProjectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PROJECTS_LIST));
        return this;
    }

    public ProjectsPage openPage() {
        driver.get(URL);
        isPageOpened();
        return this;
    }

    public ProjectPage createNewProject(String name, String code, String description){
        driver.findElement(CREATE_NEW_PROJECT_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SUBMIT_BUTTON));
        driver.findElement(PROJECT_NAME_INPUT).sendKeys(name);
        driver.findElement(PROJECT_CODE_INPUT).sendKeys(code);
        driver.findElement(PROJECT_DESCRIPTION_INPUT).sendKeys(description);
        driver.findElement(SUBMIT_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_REPOSITORY_PAGE_TITLE));
        return new ProjectPage(driver);
    }

    public boolean validateThatNewProjectIsInProjectsList(String name){
        return driver.findElement(By.xpath(String.format(newProjectName, name))).isDisplayed();
    }
    public ProjectsPage deleteProject(String name){
        driver.findElement(By.xpath(String.format(projectToggle, name))).click();
        driver.findElement(DELETE_BUTTON).click();
        driver.findElement(DELETE_CONFIRMATION_BUTTON).click();
        return this;
    }
}
