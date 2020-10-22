package pages;

import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends BasePage{
    public static String URL = "https://app.qase.io/project/";
    public static final By TEST_REPOSITORY_PAGE_TITLE = By.xpath("//*[contains(text(),'Test repository')]");
    public static final By CREATE_NEW_CASE_BUTTON = By.cssSelector(".btn.mr-2.btn-primary");
    public static final By SAVE_NEW_TEST_CASE_BUTTON = By.xpath("//*[(text() = 'Save')]");
    public static final By TEST_CASE = By.cssSelector(".case-row-title");

    public ProjectPage (WebDriver driver){
        super(driver);
    }

    @Step("Validation that the web page is opened")
    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_REPOSITORY_PAGE_TITLE));
        return this;
    }

    @Step("Open web page")
    public ProjectPage openPage(String projectName) {
        driver.get(URL + projectName);
        isPageOpened();
        return this;
    }

    public ProjectPage clickOnNewCaseCreatingButton(){
        driver.findElement(CREATE_NEW_CASE_BUTTON).click();
        return this;
    }
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

    public ProjectPage clickOnSaveButton(){
        driver.findElement(SAVE_NEW_TEST_CASE_BUTTON).click();
        return this;
    }

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
}
