package pages;

import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2
public class ProjectPage extends BasePage{
    public static String URL = "project/";
    public static final By TEST_REPOSITORY_PAGE_TITLE = By.xpath("//*[contains(text(),'Test repository')]");
    public static final By CREATE_NEW_CASE_BUTTON = By.cssSelector(".btn.mr-2.btn-primary");
    public static final By SAVE_NEW_TEST_CASE_BUTTON = By.xpath("//*[(text() = 'Save')]");
    public static final By TEST_CASE = By.cssSelector(".case-row-title");
    public static final By DELETE_TEST_CASE_BUTTON = By.cssSelector("button[title='Delete case']");
    public static final By DELETE_CONFIRMATION_BUTTON = By.xpath("//button[contains(@class, 'btn-danger') and contains(text(),'Delete')]");

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

    public ProjectPage refreshPage(){
        driver.navigate().refresh();
        return this;
    }

    @Step("Click button to create test case")
    public ProjectPage clickOnNewCaseCreatingButton(){
        driver.findElement(CREATE_NEW_CASE_BUTTON).click();
        return this;
    }

    @Step("Setting parameters for a new test case")
    public ProjectPage addTestCaseParameters(TestCase testCase){
        new Input(driver, "Title").write(testCase.getTitle());
        new Input(driver, "Description").write(testCase.getDescription());
        new Select(driver, "Status").select(testCase.getStatus());
        new Select(driver, "Suite").select(testCase.getSuite());
        new Select(driver, "Severity").select(testCase.getSeverity());
        new Select(driver, "Priority").select(testCase.getPriority());
        new Select(driver, "Type").select(testCase.getType());
        new Select(driver, "Milestone").select(testCase.getMilestone());
        new Select(driver, "Behavior").select(testCase.getBehavior());
        new Select(driver, "Automation status").select(testCase.getAutomationStatus());
        new Input(driver, "Pre-conditions").write(testCase.getPreConditions());
        new Input(driver, "Post-conditions").write(testCase.getPostConditions());
        return this;
    }

    @Step("Click on \"Save\" button")
    public ProjectPage clickOnSaveButton(){
        driver.findElement(SAVE_NEW_TEST_CASE_BUTTON).click();
        return this;
    }

    @Step("Validation that new case is crated")
    public boolean validateThatNewCaseIsCreated(String testCase) {
        boolean condition = false;
        for (int i = 0; i <= driver.findElements(TEST_CASE).size(); i++) {
            String testCaseName = driver.findElement(TEST_CASE).getText();
            if (testCaseName.equals(testCase)) {
                condition = true;
            }
        }
        return condition;
    }

    @Step("Delete test case")
    public ProjectPage deleteTestCase(String testCase) {
        List<WebElement> testCases = driver.findElements(TEST_CASE);
        for (WebElement element: testCases) {
            String testCaseName = element.getText();
            log.info("Test case: " + testCaseName);
            if (testCaseName.equals(testCase)) {
                element.click();
                driver.findElement(DELETE_TEST_CASE_BUTTON).click();
                driver.findElement(DELETE_CONFIRMATION_BUTTON).click();
                log.info(String.format("Test case \"%s\" was deleted", testCaseName));
            }
        }
        return this;
    }

    @Step("Validation that the case does not exist anymore")
    public ProjectPage validateThatCaseDoesNotExist(String testCase) {

        List<WebElement> testCases = driver.findElements(TEST_CASE);
        int count = 0;
        for (WebElement element: testCases) {
            String testCaseName = element.getText();
            log.info("Test case: " + testCaseName);
            if (testCaseName.equals(testCase)) {
                log.error(String.format("Test case \"%s\" still exists", testCase));
                count++;
            }
        }
        Assert.assertEquals(count, 0);
        return this;
    }
}
