package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;
import static tests.BaseTest.URN;

public class ProjectPage extends BasePage{
    public static String URL = "project/";
    //public static String projectName = "//*[contains(text(),'%s')]";
    public static String PROJECT_NAME = ".subheader";
    public static final By TEST_REPOSITORY_PAGE_TITLE = By.xpath("//*[contains(text(),'Test repository')]");

    public ProjectPage (WebDriver driver){
        super(driver);
    }

    @Step("Validation that the project is opened")
    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_REPOSITORY_PAGE_TITLE));
        return this;
    }

    @Step("Open page with {projectName} project")
    public ProjectPage openPage(String projectName) {
        driver.get(URN + URL + projectName);
        isPageOpened();
        return this;
    }

    public void validateThatProjectIsOpened(String projectName){
        assertEquals(driver.findElement(By.cssSelector(PROJECT_NAME)).getText(), projectName.toUpperCase());
    }
}
